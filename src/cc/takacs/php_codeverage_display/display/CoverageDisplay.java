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
    private ConfigValues configValues;

    public CoverageDisplay(Editor editor, ConfigValues configValues) {
        this.configValues = configValues;
        fileCoverage = new FileCoverage();
        coverageHighlighter = new CoverageHighlighter(editor, configValues);
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

        //If the coverage.xml file has any lines AND if highlight is enabled
        if (fileCoverage.hasLines() && configValues.isEnabled()) {
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
                        this.coverageHighlighter.highlightLines(configValues.getCoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    } else {
                        this.coverageHighlighter.highlightLines(configValues.getUncoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    }

                    lastLineExecuted = lineCoverage.getExecuted();
                    lastDifferentLine = lineNumber;
                    lastLineWasExecuted = lineCoverage.isExecuted();
                }

                lastLineNumber = lineNumber;

                if (!iterator.hasNext()) {
                    if (lastLineWasExecuted) {
                        this.coverageHighlighter.highlightLines(configValues.getCoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    } else {
                        this.coverageHighlighter.highlightLines(configValues.getUncoveredColor(), lastDifferentLine, lastLineNumber, lastLineExecuted);
                    }
                }
            }
        }
    }

    private void clear() {
        coverageHighlighter.clear();
    }
}