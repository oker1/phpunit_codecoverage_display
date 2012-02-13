package cc.takacs.php_codeverage_display.displaymap;

import cc.takacs.php_codeverage_display.display.CoverageDisplay;

import java.io.File;
import java.io.IOException;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CanonicalDisplayMapDecorator implements FilenameDisplayMap {
    private FilenameDisplayMap decorated;

    public CanonicalDisplayMapDecorator(FilenameDisplayMap decorated) {
        this.decorated = decorated;
    }

    public void add(String filename, CoverageDisplay display) {
        this.decorated.add(convertPathToCanonical(filename), display);
    }

    public void remove(String filename) {
        this.decorated.remove(convertPathToCanonical(filename));
    }

    public CoverageDisplay get(String filename) {
        return this.decorated.get(filename);
    }

    private String convertPathToCanonical(String filename) {
        try {
            return new File(filename).getCanonicalPath();
        } catch (IOException e) {
            return filename;
        }
    }
}
