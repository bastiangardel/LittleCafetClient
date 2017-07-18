package tools;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bastiangardel on 18.07.17.
 */
public class ConfigLoader {

    private HashMap<String,String> config;

    private static ConfigLoader ourInstance;

    public static ConfigLoader getInstance(){

        if (ourInstance == null)
            ourInstance = new ConfigLoader();

        return ourInstance;
    }

    public HashMap<String, String> getConfig() {
        return config;
    }

    private ConfigLoader() {

        config = new HashMap<>();

        SAXBuilder sxb = new SAXBuilder();



        try {
            Document document = sxb.build(new File("configfile"));

            Element racine = document.getRootElement();

            List listconfig = racine.getChildren("config");

            Iterator i = listconfig.iterator();

            while(i.hasNext()) {
                Element courant = (Element) i.next();

                config.put(courant.getName(), courant.getChild(courant.getName()).getText());

            }

        } catch (JDOMException  | IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("!! Error !!");
            alert.setHeaderText("Loading Config Error");
            alert.setContentText(e.getMessage());


            // Create expandable Exception.
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String exceptionText = sw.toString();

            Label label = new Label("The exception stacktrace was:");

            TextArea textArea = new TextArea(exceptionText);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();

            System.exit(1);
        }


    }

}
