package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMTalonSRX;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.simulation.AnalogGyroSim;
import edu.wpi.first.wpilibj.simulation.DifferentialDrivetrainSim;
import edu.wpi.first.wpilibj.simulation.EncoderSim;
import edu.wpi.first.wpilibj.smartdashboard.Field2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.system.LinearSystem;
import edu.wpi.first.wpilibj.system.plant.DCMotor;
import edu.wpi.first.wpilibj.system.plant.LinearSystemId;
import edu.wpi.first.wpiutil.math.numbers.N2;
import frc.robot.Constants;
import frc.robot.lib.Physics;

public class TankDrive { 
    // Drive Train
    private final SpeedController frontLeftMotor = new PWMTalonSRX(Constants.getLeftMotorController(0));
    private final SpeedController backLeftMotor = new PWMTalonSRX(Constants.getLeftMotorController(1));
    private final SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotor, backLeftMotor);

    private final SpeedController frontRightMotor = new PWMTalonSRX(Constants.getRightMotorController(0));
    private final SpeedController backRightMotor = new PWMTalonSRX(Constants.getRightMotorController(1));
    private final SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotor, backRightMotor);

    private final Encoder leftEncoder = new Encoder(
        Constants.getLeftMotorEncoder(0), 
        Constants.getLeftMotorEncoder(1)
    );

    private final Encoder rightEncoder = new Encoder(
        Constants.getRightMotorEncoder(0), 
        Constants.getRightMotorEncoder(1)
    );
    
    private final AnalogGyro gyro = new AnalogGyro(Constants.GYRO01);

    private final DifferentialDrive mDrive = new DifferentialDrive(leftMotors, rightMotors);

    private final DifferentialDriveOdometry mOdometry = new DifferentialDriveOdometry(gyro.getRotation2d());

    /** 
     * These soften the values coming from the inputs.   
     * 
     * Currently set to 3 seconds initially.
     */
    private final SlewRateLimiter leftSpeedLimiter = new SlewRateLimiter(Constants.JOYSTICKRATELIMIT);
    private final SlewRateLimiter rightSpeedLimiter = new SlewRateLimiter(Constants.JOYSTICKRATELIMIT);

    /**
     * These apply PID (Proportional, Integral, and Derivative) modifications to help 
     * get closer to the user wants vs the raw data.   
     * 
     * Right now P is the only thing enabled.
     */
    private final PIDController leftPIDController = new PIDController(
        Constants.DRIVEP,
        Constants.DRIVEI,
        Constants.DRIVED
    );
    private final PIDController rightPIDController = new PIDController(
        Constants.DRIVEP,
        Constants.DRIVEI,
        Constants.DRIVED
    );

    double angularVelocity = Physics.angularVelocity(Constants.CIM_RPMS_AT_TORQUE);
    double linearVelocity = Physics.linearVelocity(angularVelocity, Constants.WHEELRADIUS);
    double angularAcceleration = Physics.angularAcceleration(0, angularVelocity, 1.0);
    double linearAcceleration = Physics.linearAcceleration(angularAcceleration, Constants.WHEELRADIUS);

    double kaLinear = Constants.CIM_VOLTS_AT_TORQUE / angularVelocity;
    double kvLinear = Constants.CIM_VOLTS_AT_TORQUE / linearVelocity;
    double kaAngular = Constants.CIM_VOLTS_AT_TORQUE / angularAcceleration;
    double kvAngular = Constants.CIM_VOLTS_AT_TORQUE / linearAcceleration;

    private final LinearSystem<N2, N2, N2> drivetrainSystem = 
        LinearSystemId.identifyDrivetrainSystem(
            Constants.KVLINEAR, 
            Constants.KAANGULAR, 
            Constants.KVANGULAR, 
            Constants.KAANGULAR    
        );
    private final DifferentialDrivetrainSim drivetrainSim = new DifferentialDrivetrainSim(
        drivetrainSystem, 
        DCMotor.getCIM(2), 
        4.67, 
        Constants.TRACKWIDTH, 
        Constants.WHEELRADIUS, 
        null
    );

    private final AnalogGyroSim gyroSim = new AnalogGyroSim(gyro);
    private final EncoderSim leftEncoderSim = new EncoderSim(leftEncoder);
    private final EncoderSim rightEncoderSim = new EncoderSim(rightEncoder);

    private final Field2d gameField = new Field2d();

    public TankDrive() {
        // meters/pulse Distance of one wheel rotation divided by the encoder resolution
        double distancePerPulse = ((2 * Constants.WHEELRADIUS) * Math.PI) / Constants.ENCODERRESOLUTION;
        
        rightMotors.setInverted(true);

        leftEncoder.setDistancePerPulse(distancePerPulse);
        rightEncoder.setDistancePerPulse(distancePerPulse);

        leftEncoder.reset();
        rightEncoder.reset();

        SmartDashboard.putData("Game Field", gameField);

        SmartDashboard.putNumber("kaLinear: ", kaLinear);
        SmartDashboard.putNumber("kvLinear: ", kvLinear);
        SmartDashboard.putNumber("kaAngular: ", kaAngular);
        SmartDashboard.putNumber("kvAngular: ", kvAngular);
    }

    public void drive(double leftSpeed, double rightSpeed) {
        // Apply joystick inputs with PID adjustments 
        double adjustedLeftSpeed = leftPIDController.calculate(leftSpeedLimiter.calculate(leftSpeed));
        double adjustedRightSpeed = rightPIDController.calculate(rightSpeedLimiter.calculate(rightSpeed));

        mDrive.tankDrive(adjustedLeftSpeed, adjustedRightSpeed);
    }

    public double leftDistance() {
        return leftEncoder.getDistance();
    }

    public double rightDistance() {
        return rightEncoder.getDistance();
    }

    /** Update robot odometry. */
    public void updateOdometry() {
        mOdometry.update(
            gyro.getRotation2d(), 
            leftEncoder.getDistance(), 
            rightEncoder.getDistance()
        );
    }
  
    /** Resets robot odometry. */
    public void resetOdometry(Pose2d pose) {
        leftEncoder.reset();
        rightEncoder.reset();
      
        drivetrainSim.setPose(pose);
      
        mOdometry.resetPosition(pose, gyro.getRotation2d());
    }

    /** Update our simulation. This should be run every robot loop in simulation. */
    public void simulationPeriodic() {
        // To update our simulation, we set motor voltage inputs, update the
        // simulation, and write the simulated positions and velocities to our
        // simulated encoder and gyro. We negate the right side so that positive
        // voltages make the right side move forward.
        drivetrainSim.setInputs(
            leftMotors.get() * RobotController.getInputVoltage(),
            -rightMotors.get() * RobotController.getInputVoltage());
        drivetrainSim.update(0.02);
  
        leftEncoderSim.setDistance(drivetrainSim.getLeftPositionMeters());
        leftEncoderSim.setRate(drivetrainSim.getLeftVelocityMetersPerSecond());
        rightEncoderSim.setDistance(drivetrainSim.getRightPositionMeters());
        rightEncoderSim.setRate(drivetrainSim.getRightVelocityMetersPerSecond());
        gyroSim.setAngle(-drivetrainSim.getHeading().getDegrees());
    }

    /** Update odometry - this should be run every robot loop. */
    public void periodic() {
        updateOdometry();
      
        gameField.setRobotPose(mOdometry.getPoseMeters());
    }
}
