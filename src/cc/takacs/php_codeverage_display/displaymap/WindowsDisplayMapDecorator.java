package cc.takacs.php_codeverage_display.displaymap;

import cc.takacs.php_codeverage_display.display.CoverageDisplay;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class WindowsDisplayMapDecorator implements FilenameDisplayMap
{
    private FilenameDisplayMap decorated;

    public WindowsDisplayMapDecorator(FilenameDisplayMap decorated) {
        this.decorated = decorated;
    }

    public void add(String filename, CoverageDisplay display) {
        this.decorated.add(filename, display);
    }

    public void remove(String filename) {
        this.decorated.remove(filename);
    }

    public CoverageDisplay get(String filename) {
        return this.decorated.get(normalizeWindowsPHPUnitPath(filename));
    }

    private String normalizeWindowsPHPUnitPath(String filename)
    {
        if (filename.charAt(0) == '/') {
            filename = filename.substring(1);
        }

        filename = filename.replaceAll("\\\\", "/");

        return filename;
    }
}
