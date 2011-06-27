package cc.takacs.php_codeverage_display.display;

import cc.takacs.php_codeverage_display.clover.FileCoverage;
import cc.takacs.php_codeverage_display.clover.LineCoverage;
import cc.takacs.php_codeverage_display.config.ConfigValues;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;

import java.util.Iterator;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CoverageDisplay implements DocumentListener {
    private FileCoverage fileCoverage;
    private CoverageHighlighter coverageHighlighter;

    public CoverageDisplay(Editor editor) {
        fileCoverage = new FileCoverage();
        coverageHighlighter = new CoverageHighlighter(editor);
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

        if (fileCoverage.hasLines()) {
            int firstLineNumber = fileCoverage.getKeys().iterator().next();
            boolean lastLineWasExecuted = fileCoverage.getLine(firstLineNumber).isExecuted();
            int lastDifferentLine = firstLineNumber;
            int lastLineNumber = firstLineNumber - 1;
            int lastLineExecuted = fileCoverage.getLine(firstLineNumber).getExecuted();

            Iterator<Integer> iterator = fileCoverage.getKeys().iterator();
            while (iterator.hasNext()) {
                int lineNumber = iterator.next();
                LineCoverage lineCoverage = fileCoverage.getLine(lineNumber);

                if (lineCoverage.isExecuted() != lastLineWasExecuted || lastLineNumber != lineNumber - 1) {
                    if (lastLineWasExecuted) {
                        this.coverageHighlighter.highlightLines(ConfigValues.getInstance().getCoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    } else {
                        this.coverageHighlighter.highlightLines(ConfigValues.getInstance().getUncoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    }

                    lastLineExecuted = lineCoverage.getExecuted();
                    lastDifferentLine = lineNumber;
                    lastLineWasExecuted = lineCoverage.isExecuted();
                }

                lastLineNumber = lineNumber;

                if (!iterator.hasNext()) {
                    if (lastLineWasExecuted) {
                        this.coverageHighlighter.highlightLines(ConfigValues.getInstance().getCoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    } else {
                        this.coverageHighlighter.highlightLines(ConfigValues.getInstance().getUncoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    }
                }
            }
        }
    }

    private void clear() {
        coverageHighlighter.clear();
    }
}