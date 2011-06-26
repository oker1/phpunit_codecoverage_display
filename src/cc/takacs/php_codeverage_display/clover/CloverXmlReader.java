package cc.takacs.php_codeverage_display.clover;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;

/**
 * @author Zsolt Takacs <zsolt@takacs.cc>
 */
public class CloverXmlReader {
    String filename;

    public CloverXmlReader(String filename) {
        this.filename = filename;
    }

    public CoverageCollection parse() {
        CoverageCollection coverageCollection = new CoverageCollection();

        if (filename != null) {
            try {
                FileInputStream file = new FileInputStream(filename);
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(file);

                NodeList nodes = doc.getElementsByTagName("file");
                for (int i = 0; i < nodes.getLength(); i++) {
                    Element element = (Element) nodes.item(i);

                    String coveredFilename = element.getAttribute("name");

                    FileCoverage fileCoverage = new FileCoverage();

                    NodeList lines = element.getElementsByTagName("line");
                    for (int j = 0; j < lines.getLength(); j++) {
                        Element line = (Element) lines.item(j);

                        if (line.getAttribute("type").equals("stmt")) {
                            int num = Integer.parseInt(line.getAttribute("num"));
                            int count = Integer.parseInt(line.getAttribute("count"));

                            LineCoverage lineCoverage = new LineCoverage(count);
                            fileCoverage.addLine(num, lineCoverage);
                        }
                    }

                    coverageCollection.add(coveredFilename, fileCoverage);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return coverageCollection;
    }
}
