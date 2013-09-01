package cc.takacs.php_codeverage_display.toolbar;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.config.PluginConfiguration;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;

/**
 * Created with IntelliJ IDEA.
 * User: tobias
 * Date: 2013-01-09
 * Time: 2:51 PM
 * To change this template use File | Settings | File Templates.
 */
public class ToggleEnable extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        ConfigValues config = PluginConfiguration.getConfigValues();

        //toggle
        config.enabled=!config.isEnabled();

        System.out.println("Config.enabled="+(config.isEnabled()?"True":"False"));

        PluginConfiguration.getDisplayHandler().updateDisplays();
    }
}
