package cc.takacs.php_codeverage_display.display;

import cc.takacs.php_codeverage_display.config.ConfigValues;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CoverageHighlighter {
    private Editor editor;
    private ArrayList<RangeHighlighter> highlights;

    public CoverageHighlighter(Editor editor) {
        this.editor = editor;
        highlights = new ArrayList<RangeHighlighter>();
    }

    public void highlightLines(final Color color, int fromLine, int toLine, int executed) {
        Document document = this.editor.getDocument();

        SideHighlighter sideHighlighter = new SideHighlighter();
        LineHighlighter lineHighlighter = new LineHighlighter();
        ErrorStripeMarkHighlighter errorStripeMarkHighlighter = new ErrorStripeMarkHighlighter();

        if (toLine <= document.getLineCount()) {
            TextAttributes attributes = new TextAttributes();

            RangeHighlighter highlighter = createRangeHighlighter(fromLine, toLine, attributes);

            if (ConfigValues.getInstance().highlightLines) {
                lineHighlighter.highlight(highlighter, attributes, color, executed);
            }

            if (ConfigValues.getInstance().highlightSides) {
                sideHighlighter.highlight(highlighter, attributes, color, executed);
            }

            errorStripeMarkHighlighter.highlight(highlighter, attributes, color, executed);

            highlights.add(highlighter);
        }
    }

    private RangeHighlighter createRangeHighlighter(int fromLine, int toLine, TextAttributes attributes) {
        Document document = this.editor.getDocument();

        int lineStartOffset = document.getLineStartOffset(fromLine - 1);
        int lineEndOffset = document.getLineEndOffset(toLine - 1);

        return this.editor.getMarkupModel().addRangeHighlighter(
                lineStartOffset, lineEndOffset, 3333, attributes, HighlighterTargetArea.LINES_IN_RANGE
        );
    }

    public void clear() {
        MarkupModel model = this.editor.getMarkupModel();

        for (RangeHighlighter rangeHighlighter : highlights) {
            model.removeHighlighter(rangeHighlighter);
        }

        highlights.clear();
    }
}
