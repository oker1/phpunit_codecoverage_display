package cc.takacs.php_codeverage_display.display;

import cc.takacs.php_codeverage_display.clover.FileCoverage;
import cc.takacs.php_codeverage_display.config.ConfigValues;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.editor.markup.HighlighterTargetArea;
import com.intellij.openapi.editor.markup.MarkupModel;
import com.intellij.openapi.editor.markup.RangeHighlighter;
import com.intellij.openapi.editor.markup.TextAttributes;

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
    }

    public void documentChanged(DocumentEvent event) {
        redraw();
    }

    public synchronized void redraw() {
        clear();
        
        Document document = this.editor.getDocument();

        for (int line : fileCoverage.getCoveredLines()) {
            highlightLine(document, ConfigValues.getInstance().getCoveredColor(), line);
        }
        for (int line : fileCoverage.getUncoveredLines()) {
            highlightLine(document, ConfigValues.getInstance().getUncoveredColor(), line);
        }
    }

    private void highlightLine(Document document, Color color, int line) {
        if (line <= document.getLineCount()) {
            int lineStartOffset = document.getLineStartOffset(line - 1);
            int lineEndOffset = document.getLineEndOffset(line - 1);

            TextAttributes attributes = new TextAttributes();
            attributes.setBackgroundColor(color);

            RangeHighlighter lineHighlighter = this.editor.getMarkupModel().addRangeHighlighter(
                    lineStartOffset, lineEndOffset, 3333, attributes, HighlighterTargetArea.LINES_IN_RANGE
            );

            highlights.add(lineHighlighter);
        }
    }

    public void clear()
    {
        MarkupModel model = editor.getMarkupModel();

        for (RangeHighlighter rangeHighlighter : highlights) {
            model.removeHighlighter(rangeHighlighter);
        }

        highlights.clear();
    }
}