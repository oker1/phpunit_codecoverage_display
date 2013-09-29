package cc.takacs.php_codeverage_display.config;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
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
    public JTextField remoteDir;
    public JTextField localDir;
    public JButton browseLocalDir;
    public JCheckBox dirTranslation;
    public JCheckBox useCoverageSuite;

    public ConfigPanel() {
        browseCloverXmlButton.addActionListener(
                new BrowseListener(this.cloverLocation, FileChooserDescriptorFactory.createSingleFileNoJarsDescriptor())
        );
        browseLocalDir.addActionListener(
                new BrowseListener(this.localDir, FileChooserDescriptorFactory.createSingleFolderDescriptor())
        );

        coveredColor.addMouseListener(new PickerListener(panel, coveredColor));
        uncoveredColor.addMouseListener(new PickerListener(panel, uncoveredColor));
        dirTranslation.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                localDir.setEnabled(dirTranslation.isSelected());
                remoteDir.setEnabled(dirTranslation.isSelected());
                browseLocalDir.setEnabled(dirTranslation.isSelected());
            }
        });
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

    private class BrowseListener implements ActionListener {
        JTextField field;
        FileChooserDescriptor descriptor;

        public BrowseListener(JTextField field, FileChooserDescriptor descriptor) {
            this.field = field;
            this.descriptor = descriptor;
        }

        public void actionPerformed(ActionEvent actionEvent) {
            VirtualFile[] files = FileChooser.chooseFiles(panel, descriptor);

            if (files.length > 0) {
                field.setText(files[0].getPath());
            }
        }
    }
}