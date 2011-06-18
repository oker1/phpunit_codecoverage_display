package cc.takacs.php_codeverage_display;

import cc.takacs.php_codeverage_display.listener.EditorManagerListener;
import cc.takacs.php_codeverage_display.listener.ExecListener;
import com.intellij.execution.ExecutionManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.util.messages.MessageBusConnection;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ProjectPlugin {
    private Project project;

    public ProjectPlugin(Project project)
    {
        FilenameDisplayMap map = new FilenameDisplayMap();
        this.project = project;

        registerListeners(map);
    }

    private void registerListeners(FilenameDisplayMap map) {
        FileEditorManager.getInstance(this.project).addFileEditorManagerListener(new EditorManagerListener(map));

        //VirtualFileManager.getInstance().addVirtualFileListener(new CloverXmlListener());

        final MessageBusConnection connect = this.project.getMessageBus().connect(this.project);
        connect.subscribe(ExecutionManager.EXECUTION_TOPIC, new ExecListener(map));
    }
}
