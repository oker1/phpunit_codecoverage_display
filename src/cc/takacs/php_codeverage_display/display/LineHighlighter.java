package cc.takacs.php_codeverage_display.display;

import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class LineHighlighter implements Highlighter {
    public void highlight(RangeHighlighter lineHighlighter, TextAttributes textAttributes, Color color, int executed) {
        textAttributes.setBackgroundColor(color);
    }
}
