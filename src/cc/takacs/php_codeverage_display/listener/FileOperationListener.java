package cc.takacs.php_codeverage_display.listener;

import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.openapi.vfs.VirtualFileCopyEvent;
import com.intellij.openapi.vfs.VirtualFileEvent;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileMoveEvent;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;

import java.io.File;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FileOperationListener implements VirtualFileListener {
    private DisplayHandler displayHandler;

    public FileOperationListener(DisplayHandler displayHandler) {
        this.displayHandler = displayHandler;
    }

    public void beforePropertyChange(VirtualFilePropertyEvent event) {
        if (
            event.getPropertyName().equals("name") &&
            event.getFile().getParent() != null

        ) {
            String newPath = event.getFile().getParent().getPath() + File.separator + event.getNewValue();
            displayHandler.reassignDisplay(event.getFile().getPath(), newPath);
        }
    }

    public void beforeFileMovement(VirtualFileMoveEvent event) {
        String newPath = event.getNewParent().getPath() + File.separator + event.getFileName();
        displayHandler.reassignDisplay(event.getFile().getPath(), newPath);
    }

    public void contentsChanged(VirtualFileEvent event) {
        displayHandler.redrawIfXmlChanged(event.getFile().getPath());
    }

    public void fileMoved(VirtualFileMoveEvent event) {
    }

    public void propertyChanged(VirtualFilePropertyEvent event) {
    }

    public void fileCreated(VirtualFileEvent event) {
    }

    public void fileDeleted(VirtualFileEvent event) {
    }

    public void fileCopied(VirtualFileCopyEvent event) {
    }

    public void beforeContentsChange(VirtualFileEvent event) {
    }

    public void beforeFileDeletion(VirtualFileEvent event) {
    }
}
