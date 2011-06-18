package cc.takacs.php_codeverage_display.config;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigValues {
    private static ConfigValues ourInstance = new ConfigValues();

    public String cloverXmlPath;

    public ConfigValues() {
    }

    public static ConfigValues getInstance() {
        return ourInstance;
    }

    public static void setInstance(ConfigValues instance) {
        ourInstance = instance;
    }

    public void setCloverXmlPath(String cloverXmlPath) {
        this.cloverXmlPath = cloverXmlPath;
    }

    public String getCloverXmlPath() {
        return cloverXmlPath;
    }
}
