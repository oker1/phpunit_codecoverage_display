package cc.takacs.php_codeverage_display.displaymap;

import cc.takacs.php_codeverage_display.display.CoverageDisplay;

import java.util.HashMap;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class SimpleFilenameDisplayMap implements FilenameDisplayMap
{
    private HashMap<String, CoverageDisplay> map;

    public SimpleFilenameDisplayMap() {
        map = new HashMap<String, CoverageDisplay>();
    }

    public synchronized void add(String filename, CoverageDisplay display) {
        this.map.put(filename, display);
    }

    public synchronized void remove(String filename) {
        this.map.remove(filename);
    }

    public synchronized CoverageDisplay get(String filename) {
        return this.map.get(filename);
    }
}
