package cc.takacs.php_codeverage_display.config;

import com.intellij.util.xmlb.annotations.Transient;

import java.awt.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigValues {
    private static ConfigValues ourInstance = new ConfigValues();

    public String cloverXmlPath = "";
    public int coveredR = 0;
    public int coveredG = 255;
    public int coveredB = 0;
    public int uncoveredR = 255;
    public int uncoveredG = 0;
    public int uncoveredB = 0;
    public boolean highlightSides = false;
    public boolean highlightLines = true;

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

    @Transient
    public String getCloverXmlPath() {
        return cloverXmlPath;
    }

    @Transient
    public Color getCoveredColor() {
        return new Color(coveredR, coveredG, coveredB);
    }

    public void setCoveredColor(Color color) {
        coveredR = color.getRed();
        coveredG = color.getGreen();
        coveredB = color.getBlue();
    }

    @Transient
    public Color getUncoveredColor() {
        return new Color(uncoveredR, uncoveredG, uncoveredB);
    }

    public void setUncoveredColor(Color color) {
        uncoveredR = color.getRed();
        uncoveredG = color.getGreen();
        uncoveredB = color.getBlue();
    }
}
