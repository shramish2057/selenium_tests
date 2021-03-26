package in.shramishkafle.util;

import org.testng.reporters.XMLStringBuffer;
import org.testng.xml.LaunchSuite;
import org.testng.xml.Parser;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Collections;


/**
 * Created by mayur on 17-10-2016.
 */
public class CustomSuiteGenerator extends LaunchSuite{

    private static final Collection<String> EMPTY_CLASS_LIST= Collections.emptyList();
    private XMLStringBuffer suiteBuffer;

    protected CustomSuiteGenerator(boolean b) {
        super(b);
        suiteBuffer = new XMLStringBuffer();
        suiteBuffer.setDocType("suite SYSTEM \"" + Parser.TESTNG_DTD_URL + "\"");
    }


    public File save(File directory) {
        final File suiteFile = new File(directory, "testng.xml");

        saveSuiteContent(suiteFile);

        return suiteFile;
    }

    protected void saveSuiteContent(final File file) {

        try {
            OutputStreamWriter fw = new OutputStreamWriter(new FileOutputStream(file), Charset.forName("UTF-8"));
            String xmlop = getSuiteBuffer().getStringBuffer().toString();
            System.out.println(xmlop);
            fw.write(xmlop);
            fw.close();
        } catch (IOException ioe) {

            System.out.print(ioe.getMessage());
            ioe.printStackTrace();
        }
    }

    public XMLStringBuffer getSuiteBuffer() {
        return suiteBuffer;
    }

}
