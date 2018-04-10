/**
 * Created by jackzet on 10/04/2018.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import java.util.Date;

public class XMLBuilder {

    public File buildXML(String id, double hours, Date date, String customer, String project) {

        File file = null;

        try {
            DocumentBuilderFactory dbFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            // root element
            Element root = doc.createElement("root");
            doc.appendChild(root);

            Element idName = doc.createElement("id");
            Element dateName = doc.createElement("date");
            Element hoursName = doc.createElement("hours");

            idName.appendChild(doc.createTextNode(id));
            dateName.appendChild(doc.createTextNode(""+ date.getTime()));
            hoursName.appendChild(doc.createTextNode(""+hours));

            root.appendChild(idName);
            root.appendChild(dateName);
            root.appendChild(hoursName);



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING
            , "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //File createFile =  new File("C:\\Users\\Peter\\Desktop\\" + id + " " + date);
            //if (!createFile.exists()) file.createNewFile();
            StreamResult result = new StreamResult(new File("C:/Users/Peter/Desktop/" + id));
            transformer.transform(source, result);
            file = new File("C:/Users/Peter/Desktop/" + id);

            // Output to console for testing
            StreamResult consoleResult = new StreamResult(System.out);
            transformer.transform(source, consoleResult);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

}


