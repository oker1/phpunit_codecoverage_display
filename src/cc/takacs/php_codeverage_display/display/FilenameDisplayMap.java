package cc.takacs.php_codeverage_display.display;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public interface FilenameDisplayMap {
    void add(String filename, CoverageDisplay display);

    void remove(String filename);

    CoverageDisplay get(String filename);
}
