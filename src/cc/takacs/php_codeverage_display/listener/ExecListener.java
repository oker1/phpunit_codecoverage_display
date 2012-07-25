package cc.takacs.php_codeverage_display.listener;

import cc.takacs.php_codeverage_display.display.DisplayHandler;
import com.intellij.execution.ExecutionListener;
import com.intellij.execution.configurations.RunProfile;
import com.intellij.execution.process.ProcessHandler;
import com.intellij.execution.runners.ExecutionEnvironment;
import org.jetbrains.annotations.NotNull;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class ExecListener implements ExecutionListener {
    private DisplayHandler displayHandler;

    public ExecListener(DisplayHandler displayHandler) {
        this.displayHandler = displayHandler;
    }

    public void processTerminated(@NotNull RunProfile runProfile, @NotNull ProcessHandler processHandler) {
        if (isPHPUnitRun(runProfile)) {
            this.displayHandler.updateDisplays();
        }
    }

    private boolean isPHPUnitRun(RunProfile runProfile) {
        // hack to determine if process was a phpunit run
        final String className = runProfile.getClass().toString();
        return isPhpStorm3PhpunitRun(className) || isPhpStorm4PhpunitRun(className);
    }

    private boolean isPhpStorm4PhpunitRun(String className) {
        return className.startsWith("class com.jetbrains.php.phpunit.");
    }

    private boolean isPhpStorm3PhpunitRun(String className) {
        return className.startsWith("class com.jetbrains.php.run.phpunit.");
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

