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
            Document document = sxb.build(new File("configfil.xml"));

            Element racine = document.getRootElement();

            List listconfig = racine.getChildren();

            Iterator i = listconfig.iterator();

            while(i.hasNext()) {
                Element courant = (Element) i.next();

                config.put(courant.getName(), courant.getText());

            }

        } catch (JDOMException  | IOException e) {

            ErrorAlert.showAlert("!! Error !!","Loading Config Error", e);

            System.exit(1);
        }


    }

}
