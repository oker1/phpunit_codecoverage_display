package cc.takacs.php_codecoverage_display.display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import com.intellij.openapi.editor.Editor;
import org.mockito.Mockito;

public class CoverageDisplayHelper {
    public CoverageDisplayHelper() {
    }

    public CoverageDisplay createDisplay() {
        Editor editor = Mockito.mock(Editor.class);
        ConfigValues config = new ConfigValues();
        return new CoverageDisplay(editor, config);
    }
}