package cc.takacs.php_codeverage_display.displaymap;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class UnixToWindowsDisplayMapDecorator implements FilenameDisplayMap {
    private FilenameDisplayMap decorated;
    private ConfigValues configValues;

    public UnixToWindowsDisplayMapDecorator(FilenameDisplayMap decorated, ConfigValues configValues) {
        this.decorated = decorated;
        this.configValues = configValues;
    }

    public void add(String filename, CoverageDisplay display) {
        decorated.add(convertFilename(filename), display);
    }

    public void remove(String filename) {
        decorated.remove(convertFilename(filename));
    }

    public CoverageDisplay get(String filename) {
        return decorated.get(filename);
    }

    private String convertFilename(String filename) {
        if (filename.startsWith(configValues.mapDirectoryFrom)) {
            filename = configValues.mapDirectoryTo + "/" + filename.substring(configValues.mapDirectoryFrom.length() + 1);
        }

        return filename;
    }
}
