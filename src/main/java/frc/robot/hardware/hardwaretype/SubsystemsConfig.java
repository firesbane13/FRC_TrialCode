package frc.robot.hardware.hardwaretype;

import java.util.Map;

public class SubsystemsConfig {
    public boolean implemented;
    public Map<String, EncoderConfig> encoders;
    public Map<String, MotorControllerConfig> motorControllers;
    // public Map<String, Iterable<String>> invertMotors;

    public String toString() {
        String structureString = "";

        structureString = "implemented: " + implemented + "\n";

        if (encoders != null) {
            structureString += "    encoders: \n" + encoders.toString() + "\n";
        }

        if (motorControllers != null) {
            structureString += "    motorControllers: " + motorControllers.toString() + "\n";
        }

        /*
        if (invertMotors != null) {
            structureString += "    invertMotors: " + invertMotors + "\n";
        }
        */

        return(structureString);
    }
}
