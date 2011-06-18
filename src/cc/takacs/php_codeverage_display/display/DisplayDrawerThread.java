package cc.takacs.php_codeverage_display.display;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class DisplayDrawerThread implements Runnable {
    private CoverageDisplay display;

    public DisplayDrawerThread(CoverageDisplay display) {
        this.display = display;
    }

    public void run() {
        display.redraw();
    }
}
