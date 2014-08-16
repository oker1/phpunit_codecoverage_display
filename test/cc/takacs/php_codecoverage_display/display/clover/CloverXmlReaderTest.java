package cc.takacs.php_codecoverage_display.display.clover;

import cc.takacs.php_codeverage_display.clover.CloverXmlReader;
import cc.takacs.php_codeverage_display.clover.CoverageCollection;
import java.util.Arrays;

import cc.takacs.php_codeverage_display.clover.FileCoverage;
import junit.framework.TestCase;
import org.junit.Test;

public class CloverXmlReaderTest {
    @Test
    public void parsePhpUnitCloverFile() {
        String dataTestFile = CloverXmlReaderTest.class.getClassLoader().getResource("phpunit-clover.xml").getFile();
        CloverXmlReader cloverXmlReader = new CloverXmlReader(dataTestFile);

        CoverageCollection result = cloverXmlReader.parse();
        TestCase.assertEquals(result.getKeys().size(), 3);
        TestCase.assertTrue(result.getKeys().containsAll(Arrays.asList(
                "/home/jeremy/Workspace/Filesystem/Exception/FileNotFoundException.php",
                "/home/jeremy/Workspace/Filesystem/Exception/IOException.php",
                "/home/jeremy/Workspace/Filesystem/Filesystem.php"
        )));

        FileCoverage fileCoverage = result.get("/home/jeremy/Workspace/Filesystem/Exception/FileNotFoundException.php");
        TestCase.assertEquals(fileCoverage.getKeys().size(), 3);
        TestCase.assertTrue(fileCoverage.getKeys().containsAll(Arrays.asList(24, 25, 26)));
        TestCase.assertTrue(fileCoverage.getLine(24).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(25).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(26).isExecuted());

        fileCoverage = result.get("/home/jeremy/Workspace/Filesystem/Exception/IOException.php");
        TestCase.assertEquals(fileCoverage.getKeys().size(), 4);
        TestCase.assertTrue(fileCoverage.getKeys().containsAll(Arrays.asList(29, 31, 32, 39)));
        TestCase.assertTrue(fileCoverage.getLine(29).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(31).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(32).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(39).isExecuted());
    }

    @Test
    public void parseAtoumCloverFile() {
        String dataTestFile = CloverXmlReaderTest.class.getClassLoader().getResource("atoum-clover.xml").getFile();
        CloverXmlReader cloverXmlReader = new CloverXmlReader(dataTestFile);

        CoverageCollection result = cloverXmlReader.parse();
        TestCase.assertEquals(result.getKeys().size(), 2);
        TestCase.assertTrue(result.getKeys().containsAll(Arrays.asList(
                "/home/jdecool/Workspace/NovawayFileManagementBundle/Manager/BaseEntityWithFileManager.php",
                "/home/jdecool/Workspace/NovawayFileManagementBundle/vendor/twig/twig/lib/Twig/Extension.php"
        )));

        FileCoverage fileCoverage = result.get("/home/jdecool/Workspace/NovawayFileManagementBundle/Manager/BaseEntityWithFileManager.php");
        TestCase.assertEquals(fileCoverage.getKeys().size(), 10);
        TestCase.assertTrue(fileCoverage.getKeys().containsAll(Arrays.asList(55, 56, 59, 60, 62, 63, 64, 65, 66, 67)));
        TestCase.assertTrue(fileCoverage.getLine(55).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(56).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(59).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(60).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(62).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(63).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(64).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(65).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(66).isExecuted());
        TestCase.assertTrue(fileCoverage.getLine(67).isExecuted());

        fileCoverage = result.get("/home/jdecool/Workspace/NovawayFileManagementBundle/vendor/twig/twig/lib/Twig/Extension.php");
        TestCase.assertEquals(fileCoverage.getKeys().size(), 7);
        TestCase.assertTrue(fileCoverage.getKeys().containsAll(Arrays.asList(22, 31, 41, 61, 71, 81, 91)));
        TestCase.assertFalse(fileCoverage.getLine(22).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(31).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(41).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(61).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(71).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(81).isExecuted());
        TestCase.assertFalse(fileCoverage.getLine(91).isExecuted());
    }
}
