package cc.takacs.php_codeverage_display.clover;

import java.util.ArrayList;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FileCoverage {
    private ArrayList<Integer> coveredLines;
    private ArrayList<Integer> uncoveredLines;

    public FileCoverage(ArrayList<Integer> coveredLines, ArrayList<Integer> uncoveredLines) {
        this.coveredLines = coveredLines;
        this.uncoveredLines = uncoveredLines;
    }

    public FileCoverage() {
        this.coveredLines = new ArrayList<Integer>();
        this.uncoveredLines = new ArrayList<Integer>();
    }

    public ArrayList<Integer> getCoveredLines() {
        return coveredLines;
    }

    public ArrayList<Integer> getUncoveredLines() {
        return uncoveredLines;
    }
}
