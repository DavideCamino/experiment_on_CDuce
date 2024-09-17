import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class Formatter {

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: FormatXML <inputFileName> <outputFileName>");
            return;
        }

        String inputFileName = args[0];
        String outputFileName = args[1];

        // Create transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Configure transformer to output formatted XML
        transformer.setOutputProperty(javax.xml.transform.OutputKeys.INDENT, "yes");
        transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

        // Create input and output sources
        StreamSource source = new StreamSource(new FileReader(inputFileName));
        StreamResult result = new StreamResult(new FileWriter(outputFileName));

        // Transform and format XML
        transformer.transform(source, result);

        System.out.println("Formatted XML has been written to: " + outputFileName);
    }
}
