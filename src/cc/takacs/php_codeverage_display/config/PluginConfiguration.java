package cc.takacs.php_codeverage_display.config;

import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
@State(name = "PHPUnitCoveragePlugin", storages = {@com.intellij.openapi.components.Storage(id = "config", file = "$WORKSPACE_FILE$")})
public class PluginConfiguration implements Configurable, PersistentStateComponent<ConfigValues> {
    private ConfigPanel configPanel;
    private DisplayHandler displayHandler;
    private ConfigValues configValues;

    public PluginConfiguration(DisplayHandler displayHandler, ConfigValues configValues) {
        this.configValues = configValues;
        this.displayHandler = displayHandler;
    }

    public ConfigValues getState() {
        return configValues;
    }

    public void loadState(ConfigValues values) {
        configValues.loadFromInstance(values);
    }

    @Nls
    public String getDisplayName() {
        return "PHPUnit Coverage";
    }

    public JComponent createComponent() {
        configPanel = new ConfigPanel();

        return configPanel.panel;
    }

    public boolean isModified() {
        return
                !configPanel.cloverLocation.getText().equals(configValues.cloverXmlPath) ||
                        !configValues.getCoveredColor().equals(configPanel.coveredColor.getBackground()) ||
                        !configValues.getUncoveredColor().equals(configPanel.uncoveredColor.getBackground()) ||
                        configValues.highlightLines != configPanel.lineCheckBox.isSelected() ||
                        configValues.highlightSides != configPanel.sideCheckBox.isSelected();
    }

    public void apply() throws ConfigurationException {
        configValues.setCloverXmlPath(configPanel.cloverLocation.getText());
        configValues.setCoveredColor(configPanel.coveredColor.getBackground());
        configValues.setUncoveredColor(configPanel.uncoveredColor.getBackground());
        configValues.highlightLines = configPanel.lineCheckBox.isSelected();
        configValues.highlightSides = configPanel.sideCheckBox.isSelected();

        displayHandler.updateDisplays();
    }

    public void reset() {
        configPanel.cloverLocation.setText(configValues.cloverXmlPath);
        configPanel.coveredColor.setBackground(configValues.getCoveredColor());
        configPanel.uncoveredColor.setBackground(configValues.getUncoveredColor());
        configPanel.sideCheckBox.setSelected(configValues.highlightSides);
        configPanel.lineCheckBox.setSelected(configValues.highlightLines);
    }

    public void disposeUIResources() {
    }

    public Icon getIcon() {
        return null;
    }

    public String getHelpTopic() {
        return null;
    }
}
