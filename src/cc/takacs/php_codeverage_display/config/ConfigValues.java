package cc.takacs.php_codeverage_display.config;

import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.editor.colors.ColorKey;
import com.intellij.openapi.editor.colors.EditorColorsManager;
import com.intellij.openapi.editor.colors.EditorColorsScheme;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.editor.colors.ex.DefaultColorSchemesManager;
import com.intellij.openapi.editor.colors.impl.DefaultColorsScheme;
import com.intellij.rt.coverage.data.LineCoverage;
import com.intellij.util.xmlb.annotations.Transient;

import java.awt.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigValues {
    public static final String CONFIG_ENABLED = "cc.takacs.php_codeverage_display.enabled";
    public String cloverXmlPath = "";
    public int coveredR = 0;
    public int coveredG = 255;
    public int coveredB = 0;
    public int coveredA = 255; // opaque by default
    public int uncoveredR = 255;
    public int uncoveredG = 0;
    public int uncoveredB = 0;
    public int uncoveredA = 255; // opaque by default
    public boolean highlightSides = false;
    public boolean highlightLines = true;
    public boolean highlightErrors = false;
    public boolean directoryMapping = false;
    public String mapDirectoryFrom = "";
    public String mapDirectoryTo = "";
    public boolean useColorScheme = true;

    //Should we use the php storm coverage suite?
    public boolean useCoverageSuite = true;


    public void loadFromInstance(ConfigValues values) {
        cloverXmlPath = values.cloverXmlPath;
        coveredR = values.coveredR;
        coveredG = values.coveredG;
        coveredB = values.coveredB;
        coveredA = values.coveredA;
        uncoveredR = values.uncoveredR;
        uncoveredG = values.uncoveredG;
        uncoveredB = values.uncoveredB;
        uncoveredA = values.uncoveredA;
        highlightSides = values.highlightSides;
        highlightLines = values.highlightLines;
        highlightErrors = values.highlightErrors;
        directoryMapping = values.directoryMapping;
        mapDirectoryFrom = values.mapDirectoryFrom;
        mapDirectoryTo = values.mapDirectoryTo;
        useCoverageSuite = values.useCoverageSuite;
        useColorScheme = values.useColorScheme;
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
        return new Color(coveredR, coveredG, coveredB, coveredA);
    }

    public void setCoveredColor(Color color) {
        coveredR = color.getRed();
        coveredG = color.getGreen();
        coveredB = color.getBlue();
        coveredA = color.getAlpha();
    }

    @Transient
    public Color getUncoveredColor() {
        return new Color(uncoveredR, uncoveredG, uncoveredB, uncoveredA);
    }

    public void setUncoveredColor(Color color) {
        uncoveredR = color.getRed();
        uncoveredG = color.getGreen();
        uncoveredB = color.getBlue();
        uncoveredA = color.getAlpha();
    }

    @Transient
    public Color getCoveredColorToDraw() {
        if (useColorScheme) {
            EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
            return scheme.getAttributes(TextAttributesKey.find("LINE_FULL_COVERAGE")).getForegroundColor();

        } else {
            return new Color(coveredR, coveredG, coveredB, coveredA);
        }
    }

    @Transient
    public Color getUncoveredColorToDraw() {
        if (useColorScheme) {
            EditorColorsScheme scheme = EditorColorsManager.getInstance().getGlobalScheme();
            return scheme.getAttributes(TextAttributesKey.find("LINE_NONE_COVERAGE")).getForegroundColor();

        } else {
            return new Color(coveredR, coveredG, coveredB, coveredA);
        }
    }

    /**
     * If we should highlight lines
     *
     * @return boolean
     */
    public static boolean isEnabled() {
        final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        return propertiesComponent.getBoolean(ConfigValues.CONFIG_ENABLED, true);
    }
}
