package cc.takacs.php_codeverage_display.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigPanel {
    public JPanel panel;
    private JButton browseCloverXmlButton;
    public JTextField cloverLocation;

    public ConfigPanel()
    {
        browseCloverXmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JPanel panel = ConfigPanel.this.panel;

                VirtualFile[] files = FileChooser.chooseFiles(panel, FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor());

                ConfigPanel.this.cloverLocation.setText(files[0].getPath());
            }
        });
    }
}