package cc.takacs.php_codecoverage_display.display.displaymap;

import cc.takacs.php_codecoverage_display.display.CoverageDisplayHelper;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.displaymap.CanonicalDisplayMapDecorator;
import cc.takacs.php_codeverage_display.displaymap.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.displaymap.SimpleFilenameDisplayMap;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CanonicalDisplayMapDecoratorTest {
    public static final String RELATIVE_TEST_FILENAME = "testfile";
    private FilenameDisplayMap filenameDisplayMap;
    private final CoverageDisplayHelper coverageDisplayHelper = new CoverageDisplayHelper();

    @Before
    public void setUp() {
        filenameDisplayMap = new CanonicalDisplayMapDecorator(new SimpleFilenameDisplayMap());
    }

    @Test
    public void canonicalPathIsResolvedInAdd() throws IOException {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(RELATIVE_TEST_FILENAME, display);

        TestCase.assertSame(display, filenameDisplayMap.get(resolved));
    }

    @Test
    public void testCanonicalPathIsResolvedInRemove() throws IOException {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(resolved, display);
        filenameDisplayMap.remove(RELATIVE_TEST_FILENAME);

        TestCase.assertNull(filenameDisplayMap.get(resolved));
    }

    private String calculateCanonicalPath() throws IOException {
        return new File(".").getCanonicalPath() + File.separator + RELATIVE_TEST_FILENAME;
    }
}
