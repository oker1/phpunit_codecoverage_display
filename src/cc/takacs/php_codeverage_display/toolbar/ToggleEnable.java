package cc.takacs.php_codeverage_display.toolbar;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.config.PluginConfiguration;
import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

import javax.swing.*;
import java.net.URL;


/**
 *
 * This class handles the toolbar icon.
 *
 * What to to when it is clicked?
 *
 * @author Tobias Nyholm
 */
public class ToggleEnable extends AnAction {

    public void actionPerformed(AnActionEvent e) {
        Presentation presentation= e.getPresentation();
        PluginConfiguration pc= e.getProject()
                .getComponent(PluginConfiguration.class);

        //toggle
        boolean enabled = ConfigValues.isEnabled();

        final PropertiesComponent propertiesComponent = PropertiesComponent.getInstance();
        propertiesComponent.setValue(ConfigValues.CONFIG_ENABLED, Boolean.toString(!enabled));

        new ToggleIconService().toggleIcon(presentation, !enabled);
        pc.getDisplayHandler().updateDisplays();
    }



}
