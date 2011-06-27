package cc.takacs.php_codeverage_display.display;

import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.LineMarkerRenderer;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class SideHighlighter implements Highlighter {
    public void highlight(RangeHighlighter lineHighlighter, TextAttributes textAttributes, final Color color, int executed) {
        lineHighlighter.setLineMarkerRenderer(new LineMarkerRenderer() {
            public void paint(Editor editor, Graphics graphics, Rectangle rectangle) {
                Color origColor = graphics.getColor();
                try {
                    graphics.setColor(color);
                    int lineHeight = editor.getLineHeight();
                    graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height + lineHeight);
                    graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height + lineHeight);
                } finally {
                    graphics.setColor(origColor);
                }
            }
        });
    }
}
