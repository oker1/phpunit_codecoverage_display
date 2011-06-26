package cc.takacs.php_codeverage_display.clover;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class LineCoverage {
    private int executed;

    public LineCoverage(int executed) {
        this.executed = executed;
    }

    public boolean isExecuted() {
        return executed > 0;
    }

    public int getExecuted() {
        return executed;
    }
}
