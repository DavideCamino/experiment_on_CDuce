import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class FormatXml {
    public static void main(String[] args) throws Exception {
        if(args.length < 2) {
            System.out.println("Invocare il programma con nome file input e nome file output");
            System.exit(1);
        }
        // Percorso del file XML da leggere e formattare
        String inputFilePath = args[0];
        // Percorso del file in cui salvare il risultato formattato
        String outputFilePath = args[1];

        // Creazione del transformer
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();

        // Configura il transformer per l'indentazione
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");

        // Leggi il contenuto del file XML
        FileInputStream fileInputStream = new FileInputStream(new File(inputFilePath));
        StreamSource source = new StreamSource(fileInputStream);

        // Scrivi il documento XML formattato in una StringWriter
        StringWriter stringWriter = new StringWriter();
        StreamResult result = new StreamResult(stringWriter);

        // Applica la formattazione
        transformer.transform(source, result);

        // Ottieni il documento XML formattato come stringa
        String formattedXmlString = stringWriter.toString();

        // Salva il risultato formattato su un nuovo file
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFilePath));
        fileOutputStream.write(formattedXmlString.getBytes());
        fileOutputStream.close();

        System.out.println("Il documento formattato è stato salvato in " + outputFilePath);
    }
}
