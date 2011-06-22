package cc.takacs.php_codeverage_display;

import cc.takacs.php_codeverage_display.display.DisplayHandler;
import cc.takacs.php_codeverage_display.listener.EditorManagerListener;
import cc.takacs.php_codeverage_display.listener.ExecListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ProjectPlugin {
    private Project project;

    public ProjectPlugin(Project project) {
        this.project = project;

        DisplayHandler displayHandler = new DisplayHandler(new FilenameDisplayMap());

        registerListeners(displayHandler);
    }

    private void registerListeners(DisplayHandler displayHandler) {
        final MessageBusConnection connect = this.project.getMessageBus().connect(this.project);

        connect.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecListener(displayHandler));
        connect.subscribe(EditorManagerListener.FILE_EDITOR_MANAGER, new EditorManagerListener(displayHandler));
    }
}
