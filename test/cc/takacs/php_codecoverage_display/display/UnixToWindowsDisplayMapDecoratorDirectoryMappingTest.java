package cc.takacs.php_codecoverage_display.display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.display.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.display.UnixToWindowsDisplayMapDecorator;
import junit.framework.TestCase;
import org.junit.Before;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class UnixToWindowsDisplayMapDecoratorDirectoryMappingTest extends TestCase {
    public static final String MAPPING_FROM = "C:\\Projects\\test";
    public static final String MAPPING_TO = "/home/user/workspace";

    private UnixToWindowsDisplayMapDecorator map;
    private FilenameDisplayMap decoratedMock;
    private ConfigValues config = new ConfigValues();

    private final CoverageDisplayHelper coverageDisplayHelper = new CoverageDisplayHelper();

    @Before
    public void setUp() {
        decoratedMock = mock(FilenameDisplayMap.class);

        config.mapDirectoryFrom = MAPPING_FROM;
        config.mapDirectoryTo = MAPPING_TO;

        map = new UnixToWindowsDisplayMapDecorator(decoratedMock, config);
    }

    public void testPathIsTranslatedInAdd() {
        CoverageDisplay display = coverageDisplayHelper.createDisplay();

        map.add(MAPPING_FROM + "\\folder1\\file1.php", display);

        verify(decoratedMock).add(MAPPING_TO + "/folder1/file1.php", display);
    }

    public void testPathIsTranslatedInRemove() {
        map.remove(MAPPING_FROM + "\\folder1\\file1.php");

        verify(decoratedMock).remove(MAPPING_TO + "/folder1/file1.php");
    }

    public void testPathIsNotTranslatedInGet() {
        String filename = MAPPING_FROM + "\\folder1\\file1.php";

        map.get(filename);

        verify(decoratedMock).get(filename);
    }
}
