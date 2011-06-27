package cc.takacs.php_codeverage_display.clover;

import java.util.LinkedHashMap;
import java.util.Set;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FileCoverage {
    private LinkedHashMap<Integer, LineCoverage> lines;

    public FileCoverage() {
        lines = new LinkedHashMap<Integer, LineCoverage>();
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

    public boolean hasLines() {
        return lines.size() > 0;
    }
}
