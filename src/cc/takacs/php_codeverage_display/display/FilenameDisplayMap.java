package cc.takacs.php_codeverage_display.display;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FilenameDisplayMap {
    private HashMap<String, CoverageDisplay> map;

    public FilenameDisplayMap() {
        map = new HashMap<String, CoverageDisplay>();
    }

    public synchronized void add(String filename, CoverageDisplay display) {
        this.map.put(convertPathToCanonical(filename), display);
    }

    private String convertPathToCanonical(String filename) {
        try {
            // resolve symlinks in path
            return new File(filename).getCanonicalPath();
        } catch (IOException e) {
            return "";
        }
    }

    public synchronized void remove(String filename) {
        this.map.remove(convertPathToCanonical(filename));
    }

    public synchronized CoverageDisplay get(String filename) {
        return this.map.get(filename);
    }
}
