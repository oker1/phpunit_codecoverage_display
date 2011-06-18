package cc.takacs.php_codeverage_display.clover;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CoverageCollection {
    private HashMap<String, FileCoverage> files;

    public CoverageCollection()
    {
        files = new HashMap<String, FileCoverage>();
    }

    public void add(String filename, FileCoverage coverage)
    {
        files.put(filename, coverage);
    }

    public FileCoverage get(String filename)
    {
        return files.get(filename);
    }

    public Set<String> getKeys() {
        return files.keySet();
    }
}
