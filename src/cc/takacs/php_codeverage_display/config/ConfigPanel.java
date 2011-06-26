package cc.takacs.php_codeverage_display.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.vfs.VirtualFile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ConfigPanel {
    public JPanel panel;
    private JButton browseCloverXmlButton;
    public JTextField cloverLocation;
    public JPanel coveredColor;
    public JPanel uncoveredColor;
    public JCheckBox lineCheckBox;
    public JCheckBox sideCheckBox;

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
        coveredColor.addMouseListener(new PickerListener(panel, coveredColor));
        uncoveredColor.addMouseListener(new PickerListener(panel, uncoveredColor));
    }

    private class PickerListener implements MouseListener {
        private JPanel panel;
        private JPanel picker;

        public PickerListener(JPanel panel, JPanel picker) {
            this.panel = panel;
            this.picker = picker;
        }

        public void mouseClicked(MouseEvent mouseEvent) {
            Color picked = JColorChooser.showDialog(panel, "", picker.getBackground());

            if (picked != null) {
                picker.setBackground(picked);
            }
        }

        public void mousePressed(MouseEvent mouseEvent) {
        }

        public void mouseReleased(MouseEvent mouseEvent) {
        }

        public void mouseEntered(MouseEvent mouseEvent) {
        }

        public void mouseExited(MouseEvent mouseEvent) {
        }
    }
}