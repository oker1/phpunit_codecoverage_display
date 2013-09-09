package cc.takacs.php_codeverage_display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.config.PluginConfiguration;
import cc.takacs.php_codeverage_display.display.DisplayHandler;
import cc.takacs.php_codeverage_display.listener.EditorManagerListener;
import cc.takacs.php_codeverage_display.listener.ExecListener;
import cc.takacs.php_codeverage_display.listener.FileOperationListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.util.messages.MessageBusConnection;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ProjectPlugin {
    private Project project;

    public DisplayHandler displayHandler;

    public ProjectPlugin(Project project, DisplayHandler displayHandler) {
        this.project = project;
        this.displayHandler = displayHandler;

        registerListeners(displayHandler);
    }

    private void registerListeners(DisplayHandler displayHandler) {
        final MessageBusConnection connect = this.project.getMessageBus().connect(this.project);

        connect.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecListener(displayHandler));
        connect.subscribe(EditorManagerListener.FILE_EDITOR_MANAGER, new EditorManagerListener(displayHandler));
        VirtualFileManager.getInstance().addVirtualFileListener(new FileOperationListener(displayHandler));
    }

}
