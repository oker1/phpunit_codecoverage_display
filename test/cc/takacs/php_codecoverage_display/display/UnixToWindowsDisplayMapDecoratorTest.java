package cc.takacs.php_codecoverage_display.display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.display.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.display.UnixToWindowsDisplayMapDecorator;
import junit.framework.TestCase;
import org.junit.Before;

import static org.mockito.Mockito.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class UnixToWindowsDisplayMapDecoratorTest extends TestCase {
    public static final String WINDOWS_PATH = "\\folder1\\folder2\\file.php";
    public static final String UNIX_PATH = "/folder1/folder2/file.php";

    private UnixToWindowsDisplayMapDecorator map;
    private FilenameDisplayMap decoratedMock;
    private final CoverageDisplayHelper coverageDisplayHelper = new CoverageDisplayHelper();

    @Before
    public void setUp() {
        decoratedMock = mock(FilenameDisplayMap.class);
        ConfigValues config = new ConfigValues();

        map = new UnixToWindowsDisplayMapDecorator(decoratedMock, config);
    }

    public void testPathSeparatorIsReplacedInAdd() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        map.add(WINDOWS_PATH, display);

        verify(decoratedMock).add(UNIX_PATH, display);
    }

    public void testPathSeparatorIsReplacedInRemove() {
        map.remove(WINDOWS_PATH);

        verify(decoratedMock).remove(UNIX_PATH);
    }

    public void testPathSeparatorIsNotReplacedInGet() {
        map.get(WINDOWS_PATH);

        verify(decoratedMock).get(WINDOWS_PATH);
    }

    public void testGetDelegates() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        when(decoratedMock.get(WINDOWS_PATH)).thenReturn(display);

        assertSame(display, map.get(WINDOWS_PATH));
    }

    public void testAddDelegates() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        map.add(UNIX_PATH, display);

        verify(decoratedMock).add(UNIX_PATH, display);
    }

    public void testRemoveDelegates() {
        map.remove(UNIX_PATH);

        verify(decoratedMock).remove(UNIX_PATH);
    }

    public void testDoesntMapWithEmtpyMappingConfig() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        map.add(UNIX_PATH, display);

        verify(decoratedMock).add(UNIX_PATH, display);
    }
}
