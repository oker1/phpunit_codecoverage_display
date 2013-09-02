package cc.takacs.php_codeverage_display.toolbar;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.config.PluginConfiguration;
import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

import javax.swing.*;
import java.net.URL;


/**
 * @author Tobias Nyholm
 */
public class ToggleEnable extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        ConfigValues config = new ConfigValues();
        Presentation presentation= e.getPresentation();

        //toggle
        config.enabled=!config.isEnabled();

        toggleIcon(presentation, config.enabled);

        System.out.println("Config.enabled="+(config.isEnabled()?"True":"False"));

        PluginConfiguration.getDisplayHandler().updateDisplays();


        //DisplayHandler dh= new DisplayHandler(config, e.getProject());
    }

    /**
     * Toggle the icon
     *
     * @param presentation
     * @param enabled
     */
    private void toggleIcon(Presentation presentation, Boolean enabled){
        String location;
        if(enabled){
            location = "/icons/toolbar-toggle-disabled-icon.png";
        }
        else{
            location = "/icons/toolbar-toggle-enabled-icon.png";
        }
        URL url=getClass().getResource(location);
        presentation.setIcon(new ImageIcon(url));
    }

}
