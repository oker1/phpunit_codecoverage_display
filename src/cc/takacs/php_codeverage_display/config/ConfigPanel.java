package cc.takacs.php_codeverage_display.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigPanel {
    public JPanel panel;
    private JButton browseCloverXmlButton;
    public JTextField cloverLocation;
    private JButton pickUncovered;
    private JButton pickCovered;
    public JPanel coveredColor;
    public JPanel uncoveredColor;

    public ConfigPanel() {
        browseCloverXmlButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                JPanel panel = ConfigPanel.this.panel;

                VirtualFile[] files = FileChooser.chooseFiles(panel, FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor());

                if (files.length > 0) {
                    ConfigPanel.this.cloverLocation.setText(files[0].getPath());
                }
            }
        });
        pickCovered.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color picked = JColorChooser.showDialog(ConfigPanel.this.panel, "String", ConfigPanel.this.coveredColor.getBackground());

                if (picked != null) {
                    ConfigPanel.this.coveredColor.setBackground(picked);
                }
            }
        });
        pickUncovered.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                Color picked = JColorChooser.showDialog(ConfigPanel.this.panel, "String", ConfigPanel.this.uncoveredColor.getBackground());

                if (picked != null) {
                    ConfigPanel.this.uncoveredColor.setBackground(picked);
                }
            }
        });
    }
}