package cc.takacs.php_codeverage_display.clover;

import java.util.HashMap;
import java.util.Set;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FileCoverage {
    private HashMap<Integer, LineCoverage> lines;

    public FileCoverage() {
        lines = new HashMap<Integer, LineCoverage>();
    }

    public void addLine(int line, LineCoverage coverage) {
        lines.put(line, coverage);
    }

    public LineCoverage getLine(int line) {
        return lines.get(line);
    }

    public Set<Integer> getKeys() {
        return lines.keySet();
    }
}
