package frc.robot.lib;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Limelight class
 * 
 * The purpose of this class is to make using the Limelight easier to use.
 */
public class Limelight {
    protected NetworkTable camera = NetworkTableInstance.getDefault().getTable("limelight");

    /**
     * LED States
     */
    // Configured pipeline value, default state
    private static final int LED_DEFAULT = 0;

    // Different LED states
    private static final int LED_OFF = 1;
    private static final int LED_BLINK = 2;
    private static final int LED_ON = 3;

    /**
     * Camera modes
     */
    private static final int MODE_VISION = 0;
    private static final int MODE_DRIVER = 1;

    private Logging logging = new Logging(Logging.ENABLE_SMARTDASHBOARD);
    
    /**
     * Returns if Limelight detects a valid target.
     * 
     * @return validTarget
     */
    public boolean isValidTarget() {
        double validTarget = getValue("tv").getDouble(0.0);
        boolean isValidTarget = (validTarget == 1);

        logging.write("LL Valid Target:", validTarget);

        return isValidTarget;
    }

    /**
     * Gets angle from center of the Limelight to X-coordinate
     * 
     * @return angle
     */
    public double getXAngle() {
        double xAngle = getValue("tx").getDouble(0.00);

        logging.write("LL X Angle:", xAngle);

        return xAngle;
    }

    /**
     * Gets angle from center of the Limelight to Y-coordinate
     * 
     * @return angle
     */
    public double getYAngle() {
        double yAngle = getValue("ty").getDouble(0.00);

        logging.write("LL Y Angle:", yAngle);

        return yAngle;
    }

    /**
     * Get percentage of target compared to the camera view
     * 
     * @return targetArea
     */
    public double getTargetArea() {
        double targetArea = getValue("ta").getDouble(0.00);

        logging.write("LL Target Angle:", targetArea);

        return targetArea;
    }

    /**
     * Return the skem or rotation of the target
     * 
     * @return skewOrRotation
     */
    public double getTargetSkew() {
        double targetSkew = getValue("ts").getDouble(0.00);

        logging.write("LL Target Skew:", targetSkew);

        return targetSkew;
    }

    /**
     * Return the camera's latency
     * 
     * @return latency
     */
    public double getLatency() {
        double latency = getValue("tl").getDouble(0.00);

        logging.write("LL Latency:", latency);

        return latency;
    }

    /**
     * Turn Limelight LED on.
     */
    public void ledOn() {
        this.setLedMode(LED_ON);
    }

    /**
     * Turn Limelight LED off
     */
    public void ledOff() {
        this.setLedMode(LED_OFF);
    }

    /**
     * Blink Limelight LED
     */
    public void ledBlink() {
        this.setLedMode(LED_BLINK);
    }

    /**
     * Set to pipeline configuration.
     */
    public void ledDefault() {
        this.setLedMode(LED_DEFAULT);
    }

    /**
     * Change Limelight pipelines if desired.  
     * 
     * Pipelines contain different configurations for the Limelight.
     * 
     * @param pipeline
     */
    public void setPipeline(int pipeline) {
        getValue("pipeline").setNumber(pipeline);
    }

    /**
     * Change Limelight to track target
     */
    public void targetMode() {
        this.setCamMode(MODE_VISION);
    }

    /**
     * Change Limelight vision to allow drivers to see normally
     */
    public void driverMode() {
        this.setCamMode(MODE_DRIVER);
    }

    /**
     * Set Limelight camera mode
     * @param mode
     */
    private void setCamMode(int mode) {
        getValue("camMode").setNumber(mode);
    }

    /**
     * Set Limelight LED status.
     * 
     * @param mode
     */
    private void setLedMode(int mode) {
        getValue("ledMode").setNumber(mode);
    }

    /**
     * Retrieve value from the Limelight
     * 
     * @param key
     * 
     * @return entryObject
     */
    private NetworkTableEntry getValue(String key) {
        return camera.getEntry(key);
    }
}