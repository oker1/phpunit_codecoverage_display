package cc.takacs.php_codeverage_display;

import cc.takacs.php_codeverage_display.toolbar.ToggleIconService;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ApplicationPlugin implements ApplicationComponent {
    @Override
    public void initComponent() {
        AnAction toolbarButton = ActionManager.getInstance().getAction("cc.takacs.php_codeverage_display.toolbar.enable");
        final boolean enabled = PropertiesComponent.getInstance().getBoolean("cc.takacs.php_codeverage_display.enabled", true);
        new ToggleIconService().toggleIcon(toolbarButton.getTemplatePresentation(), enabled);
    }

    @Override
    public void disposeComponent() {
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "ApplicationPlugin";
    }
}
