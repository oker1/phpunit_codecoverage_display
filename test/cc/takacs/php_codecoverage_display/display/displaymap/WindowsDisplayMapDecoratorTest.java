package cc.takacs.php_codecoverage_display.display.displaymap;

import cc.takacs.php_codecoverage_display.display.CoverageDisplayHelper;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.displaymap.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.displaymap.SimpleFilenameDisplayMap;
import cc.takacs.php_codeverage_display.displaymap.WindowsDisplayMapDecorator;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertSame;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class WindowsDisplayMapDecoratorTest
{
    private FilenameDisplayMap object;

    private final CoverageDisplayHelper coverageDisplayHelper = new CoverageDisplayHelper();

    @Before
    public void setUp()
    {
        object = new WindowsDisplayMapDecorator(new SimpleFilenameDisplayMap());
    }

    @Test
    public void removesSlashPrefix()
    {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();
        object.add("E:/Folder/Test.php", display);

        CoverageDisplay actual = object.get("/E:/Folder/Test.php");

        assertSame(display, actual);
    }

    @Test
    public void convertsBackslashesToSlashes()
    {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();
        object.add("E:/Folder/Test.php", display);

        CoverageDisplay actual = object.get("E:\\Folder\\Test.php");

        assertSame(display, actual);
    }
}
