package frc.robot.lib;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logging {
    public static final boolean ENABLE_SMARTDASHBOARD = true;
    public static final boolean DISABLE_SMARTDASHBOARD = false;

    public static final boolean ENABLE_SHUFFLEBOARD = true;
    public static final boolean DISABLE_SHUFFLEBOARD = false;

    public static final boolean ENABLE_GLASS = true;
    public static final boolean DISABLE_GLASS = false;

    // Dashboards disabled by default
    private boolean smartDashboardEnabled = DISABLE_SMARTDASHBOARD;
    private boolean shuffleBoardEnabled = DISABLE_SHUFFLEBOARD;
    private boolean glassEnabled = DISABLE_GLASS;

    /**
     * Basic Logging constructor
     */
    public Logging() {
        // No specific logging turned on
    }

    /**
     * Logging constructor enabling or disabling SmartDashboard.
     * 
     * @param smartDashboard
     */
    public Logging(boolean smartDashboard) {
        this.smartDashboardEnabled = smartDashboard;
    }
    
    /**
     * Logging constructor to enable or disable SmartDashboard and Shuffleboard.
     * 
     * @param smartDashboard
     * @param shuffleBoard
     */
    public Logging(boolean smartDashboard, boolean shuffleBoard) {
        this.smartDashboardEnabled = smartDashboard;
        this.shuffleBoardEnabled = shuffleBoard;
    }

    /**
     * Logging constructor to enable or disable SmartDashboard, Shuffleboard, and Glass.
     * 
     * @param smartDashboard
     * @param shuffleBoard
     * @param glass
     */
    public Logging(boolean smartDashboard, boolean shuffleBoard, boolean glass) {
        this.smartDashboardEnabled = smartDashboard;
        this.shuffleBoardEnabled = shuffleBoard;
        this.glassEnabled = glass;
    }

    /**
     * Enable SmartDashboard Logging
     */
    public void enableSmartDashboard() {
        this.smartDashboardEnabled = ENABLE_SMARTDASHBOARD;
    }

    /**
     * Disable SmartDashboard Logging
     */
    public void disableSmartDashboard() {
        this.smartDashboardEnabled = DISABLE_SMARTDASHBOARD;
    }

    /**
     * Enable Shuffleboard Logging
     */
    public void enableShuffleboard() {
        this.shuffleBoardEnabled = ENABLE_SHUFFLEBOARD;
    }

    /**
     * Disable Shuffleboard Logging
     */
    public void disableShuffleboard() {
        this.shuffleBoardEnabled = DISABLE_SHUFFLEBOARD;
    }

    /**
     * Enable Glass Logging
     */
    public void enableGlass() {
        this.glassEnabled = ENABLE_GLASS;
    }

    /**
     * Disable Glass Logging
     */
    public void disableGlass() {
        this.glassEnabled = DISABLE_GLASS;
    }

    /**
     * Write to enabled logs
     * 
     * @param title
     * @param value
     */
    public void write(String title, Object value) {
        String[] classIdentifierArray = value.getClass().getName().split(".");
        String classIdentifier = classIdentifierArray[classIdentifierArray.length];

        if (this.smartDashboardEnabled) {
            if (classIdentifier.equals("String")) {
                SmartDashboard.putString(title, String.valueOf(value));
            } else if (classIdentifier.equals("Integer")
                || classIdentifier.equals("Double")
            ) {
                SmartDashboard.putNumber(title, Double.valueOf(value.toString()));
            } else {
                SmartDashboard.putString(title, value.toString());
            }
        }

        if (shuffleBoardEnabled) {
            // To be filled in soon.
        }

        if (this.glassEnabled) {
            // To be filled in soon.
        }
    }
}
