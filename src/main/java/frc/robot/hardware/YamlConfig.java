package frc.robot.hardware;

import java.io.InputStream;

import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.BeanAccess;
import org.yaml.snakeyaml.representer.Representer;

import frc.robot.hardware.hardwaretype.SubsystemsConfig;

public class YamlConfig {
    private String $extends;
    private Integer teamNumber;
    private String teamName;
    private String robotName;
    private Map<String, SubsystemsConfig> subsystems;

    public static YamlConfig loadFrom(InputStream inputStream) {
        var representer = new Representer();

        representer.getPropertyUtils().setSkipMissingProperties(true);

        Yaml yaml = new Yaml(new Constructor(YamlConfig.class), representer);
        
        yaml.setBeanAccess(BeanAccess.FIELD);

        YamlConfig loadedConfig = yaml.load(inputStream);

        if (loadedConfig.$extends != null && !loadedConfig.$extends.equals("")) {
            var baseConfig = YamlConfig.class.getClassLoader().getResourceAsStream(loadedConfig.$extends + ".yml");

            // loadedConfig = merge(loadedConfig, loadRaw(baseConfig));
        }

        return loadedConfig;
    }

    static YamlConfig loadRaw(InputStream input) {
        Representer representer = new Representer();

        representer.getPropertyUtils().setSkipMissingProperties(true);
        
        Yaml yaml = new Yaml(new Constructor(YamlConfig.class), representer);

        yaml.setBeanAccess(BeanAccess.FIELD);

        return yaml.load(input);
    }

    public String toString() {
        return (
            "YamlConfig {\n" +
            "   $extends: " + $extends + "\n" +
            "   teamNumber: " + teamNumber + "\n" + 
            "   teamName: " + teamName + "\n" + 
            "   robotName: " + robotName + "\n" + 
            "   subsystems: \n" + subsystems.toString() + "\n"
        );
    }
}
