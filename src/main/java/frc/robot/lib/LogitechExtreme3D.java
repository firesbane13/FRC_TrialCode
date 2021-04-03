package frc.robot.lib;

import edu.wpi.first.wpilibj.Joystick;

/**
 * Used to access Joystick buttons easier
 */
public class LogitechExtreme3D extends Joystick {

    /**
     * Used to initiate the Logitech Extreme 3D
     *
     * @param port
     */
    public LogitechExtreme3D(int port) {
        // Needed to allow "extends Joystick"
        super(port);
    }

    /**
     * Get the angle of the Hat or thumb analog stick at the top of the joystick
     * 
     *       0
     *       |
     * 270 --+-- 90
     *       |
     *      180
     * 
     * @return
     */
    public int getHat() {
        return getPOV();
    }

    /**
     * Check if button is pressed.
     * 
     * @param buttonNumber
     * 
     * @return buttonPressed
     */
    public boolean pressed(int buttonNumber) {
        return getButtonPressed(buttonNumber);
    }

    /**
     * Check if button released.
     * 
     * @param buttonNumber
     * 
     * @return buttonReleased
     */
    public boolean released(int buttonNumber) {
        return getButtonReleased(buttonNumber);
    }

    /**
     * Check if button is pressed through parent class.
     * 
     * @param buttonNumber
     * 
     * @return
     */
    private boolean getButtonPressed(int buttonNumber) {
        return this.getRawButtonPressed(buttonNumber);
    }

    /**
     * Check if button is released through parent class.
     * 
     * @param buttonNumber
     * 
     * @return
     */
    private boolean getButtonReleased(int buttonNumber) {
        return this.getRawButtonReleased(buttonNumber);
    }
}
