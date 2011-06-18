package cc.takacs.php_codeverage_display.config;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import org.jetbrains.annotations.Nls;

import javax.swing.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
@State(name="PHPUnitCoveragePlugin", storages={@com.intellij.openapi.components.Storage(id="config", file="$WORKSPACE_FILE$")})
public class PluginConfiguration implements Configurable, PersistentStateComponent<ConfigValues> {
    private ConfigPanel configPanel;

    public PluginConfiguration() {
        configPanel = new ConfigPanel();
    }

    public ConfigValues getState() {
        return ConfigValues.getInstance();
    }

    public void loadState(ConfigValues configValues) {
        ConfigValues.setInstance(configValues);
    }

    @Nls
    public String getDisplayName() {
        return "PHPUnit Coverage";
    }

    public JComponent createComponent() {
        return configPanel.panel;
    }

    public boolean isModified() {
        return !configPanel.cloverLocation.getText().equals(ConfigValues.getInstance().cloverXmlPath);
    }

    public void apply() throws ConfigurationException {
        ConfigValues.getInstance().setCloverXmlPath(configPanel.cloverLocation.getText());
    }

    public void reset() {
        configPanel.cloverLocation.setText(ConfigValues.getInstance().cloverXmlPath);
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
