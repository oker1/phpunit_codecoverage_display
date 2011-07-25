package cc.takacs.php_codecoverage_display.display;

import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.display.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.display.SimpleFilenameDisplayMap;
import junit.framework.TestCase;
import org.junit.Before;

import java.io.File;
import java.io.IOException;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class SimpleFilenameDisplayMapTest extends TestCase {
    public static final String TEST_FILENAME = File.separator + "testfile";
    public static final String RELATIVE_TEST_FILENAME = "testfile";
    private FilenameDisplayMap filenameDisplayMap;
    private final CoverageDisplayHelper coverageDisplayHelper = new CoverageDisplayHelper();

    @Before
    public void setUp() {
        filenameDisplayMap = new SimpleFilenameDisplayMap();
    }

    public void testAddedDisplayCanBeFound() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        filenameDisplayMap.add(TEST_FILENAME, display);

        assertSame(display, filenameDisplayMap.get(TEST_FILENAME));
    }

    public void testRemovedDisplayIsNotReturned() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        filenameDisplayMap.add(TEST_FILENAME, display);
        filenameDisplayMap.remove(TEST_FILENAME);

        assertNull(filenameDisplayMap.get(TEST_FILENAME));
    }

    public void testNullIsReturnedForNotAddedFilename() {
        assertNull(filenameDisplayMap.get(TEST_FILENAME));
    }

    public void testCanonicalPathIsResolvedInAdd() throws IOException {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(RELATIVE_TEST_FILENAME, display);

        assertSame(display, filenameDisplayMap.get(resolved));
    }

    public void testCanonicalPathIsResolvedInRemove() throws IOException {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(resolved, display);
        filenameDisplayMap.remove(RELATIVE_TEST_FILENAME);

        assertNull(filenameDisplayMap.get(resolved));
    }

    private String calculateCanonicalPath() throws IOException {
        return new File(".").getCanonicalPath() + File.separator + RELATIVE_TEST_FILENAME;
    }
}
