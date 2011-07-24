package cc.takacs.php_codeverage_display.display;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class SimpleFilenameDisplayMap implements FilenameDisplayMap {
    private HashMap<String, CoverageDisplay> map;

    public SimpleFilenameDisplayMap() {
        map = new HashMap<String, CoverageDisplay>();
    }

    public synchronized void add(String filename, CoverageDisplay display) {
        try {
            String resolvedFilename = convertPathToCanonical(filename);
            this.map.put(resolvedFilename, display);
        } catch (IOException e) {
        }
    }

    private String convertPathToCanonical(String filename) throws IOException {
        return new File(filename).getCanonicalPath();
    }

    public synchronized void remove(String filename) {
        try {
            String resolvedFilename = convertPathToCanonical(filename);
            this.map.remove(resolvedFilename);
        } catch (IOException e) {
        }
    }

    public synchronized CoverageDisplay get(String filename) {
        return this.map.get(filename);
    }
}
