package frc.robot;

public final class Constants {
    /*************************************** 
     * JOYSTICKS / CONTROLLERS
     */
    public static final int JOYSTICK01 = 1;

    public static final double JOYSTICKRATELIMIT = 3;

    /*************************************** 
     * GYROSCOPES
     */
    public static final int GYRO01 = 0;

    /************************************** 
     * DRIVE TRAIN / MOVEMENT
     */

     public static final double DRIVEP = 1;
     public static final double DRIVEI = 0;
     public static final double DRIVED = 0;

    /**
     * Identify a standard differential drive drivetrain, given the drivetrain's kV and kA in both linear (volts/(meter/sec) and volts/(meter/sec^2)) and angular (volts/(radian/sec) and volts/(radian/sec^2)) cases. This can be found using frc-characterization. The states of the system are [left velocity, right velocity]^T, inputs are [left voltage, right voltage]^T, and outputs are [left velocity, right velocity]^T.
     * Parameters:
     *      kVLinear - The linear velocity gain, volts per (meter per second).
     *      kALinear - The linear acceleration gain, volts per (meter per second squared).
     *      kVAngular - The angular velocity gain, volts per (radians per second).
     *      kAAngular - The angular acceleration gain, volts per (radians per second squared).
     * 
     * 1.98, 0.2, 1.5, and 0.3 were from example code.    We should calculate this per robot.
     */
     public static final double KVLINEAR = 1.98;
     public static final double KALINEAR = 0.2;
     public static final double KVANGULAR = 1.5;
     public static final double KAANGULAR = 0.3;

    // PWM ports or other ports that the motors are plugged into
    protected static final int[] LEFTMOTORCONTROLLER = {0, 1, 2};
    protected static final int[] RIGHTMOTORCONTROLLER = {3, 4, 5};

    // Ports that encoders are plugged into
    protected static final int[] LEFTMOTORENCODER = {0, 1};
    protected static final int[] RIGHTMOTORENCODER = {2, 3};

    /** 
     * Wheel radius in meters.  Seems odd, but it seems like FRC code
     * wants things in meters.
     * 
     * 3 inches = 0.0762 meters
     */
    public static final double WHEELRADIUS = 0.0762;

    /**
     * Track width is the measurement from the left center wheel to the 
     * right center wheel.   Essentially the distance between robot wheel
     * sets.
     * 
     *      ====   ====   ====  ---
     *         ------------     / \
     *        |            |     |   Track width
     *         ------------     \ /
     *      ====   ====   ====  ---
     * 
     * Currently, listed in meters
     * 
     * 30 inches = 0.762 meters
     */
    public static final double TRACKWIDTH = 0.762;

    // 3 meters per second
    public static final double DRIVEMAXSPEED = 3.0;

    /**********************************************
     * ENCODERS
     */
    // Not sure what the units are, but common FRC encoders are quadrature
    public static final int ENCODERRESOLUTION = -4096;

    private Constants() {
        // Needed to deal with public constructor.
    }

    public static int getLeftMotorController(int entry) {
        return LEFTMOTORCONTROLLER[entry];
    }

    public static int getRightMotorController(int entry) {
        return RIGHTMOTORCONTROLLER[entry];
    }

    public static int getLeftMotorEncoder(int entry) {
        return LEFTMOTORENCODER[entry];
    }

    public static int getRightMotorEncoder(int entry) {
        return RIGHTMOTORENCODER[entry];
    }
}
