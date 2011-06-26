package cc.takacs.php_codeverage_display.display;

import cc.takacs.php_codeverage_display.clover.FileCoverage;
import cc.takacs.php_codeverage_display.clover.LineCoverage;
import cc.takacs.php_codeverage_display.config.ConfigValues;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.markup.*;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CoverageDisplay implements DocumentListener {
    private Editor editor;
    private FileCoverage fileCoverage;
    private ArrayList<RangeHighlighter> highlights;

    public CoverageDisplay(Editor editor) {
        this.editor = editor;
        fileCoverage = new FileCoverage();
        highlights = new ArrayList<RangeHighlighter>();
    }

    public void setFileCoverage(FileCoverage fileCoverage) {
        this.fileCoverage = fileCoverage;
    }

    public void beforeDocumentChange(DocumentEvent event) {
        clear();
    }

    public void documentChanged(DocumentEvent event) {
    }

    public synchronized void redraw() {
        clear();

        Document document = this.editor.getDocument();

        for (int lineNumber : fileCoverage.getKeys()) {
            LineCoverage lineCoverage = fileCoverage.getLine(lineNumber);

            if (lineCoverage.isExecuted()) {
                highlightLine(document, ConfigValues.getInstance().getCoveredColor(), lineNumber, lineCoverage.getExecuted());
            } else {
                highlightLine(document, ConfigValues.getInstance().getUncoveredColor(), lineNumber, lineCoverage.getExecuted());
            }
        }
    }

    private void highlightLine(Document document, final Color color, int line, int executed) {
        if (line <= document.getLineCount()) {
            int lineStartOffset = document.getLineStartOffset(line - 1);
            int lineEndOffset = document.getLineEndOffset(line - 1);

            TextAttributes attributes = new TextAttributes();
            if (ConfigValues.getInstance().highlightLines) {
                attributes.setBackgroundColor(color);
            }

            RangeHighlighter lineHighlighter = this.editor.getMarkupModel().addRangeHighlighter(
                    lineStartOffset, lineEndOffset, 3333, attributes, HighlighterTargetArea.LINES_IN_RANGE
            );

            highlights.add(lineHighlighter);

            RangeHighlighter sideHighlighter = this.editor.getMarkupModel().addRangeHighlighter(
                    lineStartOffset, lineEndOffset, 3333, null, HighlighterTargetArea.LINES_IN_RANGE
            );

            if (ConfigValues.getInstance().highlightSides) {
                lineHighlighter.setLineMarkerRenderer(new LineMarkerRenderer() {
                    public void paint(Editor editor, Graphics graphics, Rectangle rectangle) {
                        Color origColor = graphics.getColor();
                        try {
                            graphics.setColor(color);
                            graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height + editor.getLineHeight());
                            graphics.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height + editor.getLineHeight());
                        } finally {
                            graphics.setColor(origColor);
                        }
                    }
                });
            }

            sideHighlighter.setErrorStripeMarkColor(color);
            sideHighlighter.setErrorStripeTooltip(
                    executed > 0 ? "Statement executed " + executed + " times" : "Statement was not executed");

            highlights.add(sideHighlighter);
        }
    }

    private void clear() {
        MarkupModel model = editor.getMarkupModel();

        for (RangeHighlighter rangeHighlighter : highlights) {
            model.removeHighlighter(rangeHighlighter);
        }

        highlights.clear();
    }
}