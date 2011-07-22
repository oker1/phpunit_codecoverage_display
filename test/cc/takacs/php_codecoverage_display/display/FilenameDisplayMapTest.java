package cc.takacs.php_codecoverage_display.display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.display.FilenameDisplayMap;
import com.intellij.openapi.editor.Editor;
import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class FilenameDisplayMapTest extends TestCase {
    public static final String TESTFILENAME = File.separator + "testfile";
    public static final String RELATIVE_TESTFILENAME = "testfile";
    private FilenameDisplayMap filenameDisplayMap;

    @Before
    public void setUp() {
        filenameDisplayMap = new FilenameDisplayMap();
    }

    public void testAddedDisplayCanBeFound() {
        CoverageDisplay display = createDisplay();

        filenameDisplayMap.add(TESTFILENAME, display);

        assertSame(display, filenameDisplayMap.get(TESTFILENAME));
    }

    public void testRemovedDisplayIsNotReturned() {
        CoverageDisplay display = createDisplay();

        filenameDisplayMap.add(TESTFILENAME, display);
        filenameDisplayMap.remove(TESTFILENAME);

        assertNull(filenameDisplayMap.get(TESTFILENAME));
    }

    public void testNullIsReturnedForNotAddedFilename()
    {
        assertNull(filenameDisplayMap.get(TESTFILENAME));
    }

    public void testCanonicalPathIsResolvedInAdd() throws IOException {
        CoverageDisplay display = createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(RELATIVE_TESTFILENAME, display);

        assertSame(display, filenameDisplayMap.get(resolved));
    }

    public void testCanonicalPathIsResolvedInRemove() throws IOException {
        CoverageDisplay display = createDisplay();

        String resolved = calculateCanonicalPath();

        filenameDisplayMap.add(resolved, display);
        filenameDisplayMap.remove(RELATIVE_TESTFILENAME);

        assertNull(filenameDisplayMap.get(resolved));
    }

    private String calculateCanonicalPath() throws IOException {
        return new File(".").getCanonicalPath() + File.separator + RELATIVE_TESTFILENAME;
    }

    private CoverageDisplay createDisplay() {
        Editor editor = Mockito.mock(Editor.class);
        ConfigValues config = new ConfigValues();
        return new CoverageDisplay(editor, config);
    }
}
