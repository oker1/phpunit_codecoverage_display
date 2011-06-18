package cc.takacs.php_codeverage_display.listener;

import cc.takacs.php_codeverage_display.FilenameDisplayMap;
import cc.takacs.php_codeverage_display.clover.CloverXmlReader;
import cc.takacs.php_codeverage_display.clover.CoverageCollection;
import cc.takacs.php_codeverage_display.clover.FileCoverage;
import cc.takacs.php_codeverage_display.config.ConfigValues;
import cc.takacs.php_codeverage_display.display.CoverageDisplay;
import cc.takacs.php_codeverage_display.display.DisplayDrawerThread;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ExecListener implements ExecutionListener {
    private FilenameDisplayMap map;


    public ExecListener(FilenameDisplayMap map) {
        this.map = map;
    }

    private String getCloverXmlPath() {
        return ConfigValues.getInstance().getCloverXmlPath();
    }

    public void processTerminated(@NotNull RunProfile runProfile, @NotNull ProcessHandler processHandler) {
        if (isPHPUnitRun(runProfile)) {
            CloverXmlReader reader = new CloverXmlReader(getCloverXmlPath());
            CoverageCollection fileCoverages = reader.parse();

            for (String filename : fileCoverages.getKeys()) {
                FileCoverage fileCoverage = fileCoverages.get(filename);

                CoverageDisplay display = map.get(filename);

                if (display != null) {
                    display.setFileCoverage(fileCoverage);

                    SwingUtilities.invokeLater(new DisplayDrawerThread(display));
                } else {
                    System.err.println("no display found for: " + filename);
                }
            }
        }
    }

    private boolean isPHPUnitRun(RunProfile runProfile) {
        // hack to determine if process was a phpunit run
        return runProfile.getClass().toString().startsWith("class com.jetbrains.php.run.phpunit.");
    }

    public void processStartScheduled(String s, ExecutionEnvironment executionEnvironment) {
    }

    public void processStarting(String s, @NotNull ExecutionEnvironment executionEnvironment) {
    }

    public void processNotStarted(String s, @NotNull ExecutionEnvironment executionEnvironment) {
    }

    public void processStarted(String s, @NotNull ExecutionEnvironment executionEnvironment, @NotNull ProcessHandler processHandler) {
    }

    public void processTerminating(@NotNull RunProfile runProfile, @NotNull ProcessHandler processHandler) {
    }
}

