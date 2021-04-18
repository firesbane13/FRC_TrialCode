package frc.robot.hardware.hardwaretype;

public class EncoderConfig {
    private boolean implemented;
    private Integer port;
    private Double ppr;

    public String toString() {
        return (
            " implemented: " + implemented + "\n" +
            " port: " + port + "\n" +
            " ppr: " + ppr + "\n"
        );
    }
}
