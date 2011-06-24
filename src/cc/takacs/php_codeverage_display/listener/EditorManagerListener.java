package cc.takacs.php_codeverage_display.listener;

import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.File;
import java.io.IOException;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class EditorManagerListener implements FileEditorManagerListener {
    private DisplayHandler displayHandler;

    public EditorManagerListener(DisplayHandler displayHandler) {
        this.displayHandler = displayHandler;
    }

    public void fileOpened(FileEditorManager source, VirtualFile file) {
        Editor editor = null;

        for (FileEditor fileEditor : source.getEditors(file)) {
            if ((fileEditor instanceof TextEditor)) {
                editor = ((TextEditor) fileEditor).getEditor();
                break;
            }
        }

        if (editor != null) {
            try {
                // resolve symlinks in path
                String path = new File(file.getPath()).getCanonicalPath();
                this.displayHandler.addDisplayForEditor(editor, path);
            } catch (IOException e) {
            }
        }
    }

    public void fileClosed(FileEditorManager source, VirtualFile file) {
        this.displayHandler.removeDisplayForFile(file.getPath());
    }

    public void selectionChanged(FileEditorManagerEvent event) {
    }
}
