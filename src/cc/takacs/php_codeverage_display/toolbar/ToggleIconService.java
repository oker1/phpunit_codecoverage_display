package cc.takacs.php_codeverage_display.toolbar;

import com.intellij.openapi.actionSystem.Presentation;

import javax.swing.*;
import java.net.URL;

/**
 * @author Tobias Nyholm
 *
 * Toggle the icon
 */
public class ToggleIconService {

    /**
     * Toggle the icon
     *
     * @param presentation
     * @param enabled
     */
    public void toggleIcon(Presentation presentation, Boolean enabled){
        String location;
        if(enabled){
            location = "/icons/toolbar-toggle-enabled-icon.png";
        }
        else{
            location = "/icons/toolbar-toggle-disabled-icon.png";
        }
        URL url=getClass().getResource(location);
        presentation.setIcon(new ImageIcon(url));
    }
}
