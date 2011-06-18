package cc.takacs.php_codeverage_display.listener;

import cc.takacs.php_codeverage_display.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.vfs.VirtualFile;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class EditorManagerListener implements FileEditorManagerListener {
    private FilenameDisplayMap map;

    public EditorManagerListener(FilenameDisplayMap map) {
        this.map = map;
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
            CoverageDisplay display = new CoverageDisplay(editor);

            editor.getDocument().addDocumentListener(display);
            this.map.add(file.getPath(), display);
        }
    }

    public void fileClosed(FileEditorManager source, VirtualFile file) {
        this.map.remove(file.getPath());
    }

    public void selectionChanged(FileEditorManagerEvent event) {
    }
}
