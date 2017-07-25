package tools;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by bastiangardel on 18.07.17.
 */
public class ConfigLoader {

    private static ConfigLoader ourInstance;
    private HashMap<String, String> config;

    private ConfigLoader() {

        config = new HashMap<>();

        SAXBuilder sxb = new SAXBuilder();


        try {
            Document document = sxb.build(new File("configfile.xml"));

            Element racine = document.getRootElement();

            List listconfig = racine.getChildren();

            Iterator i = listconfig.iterator();

            while (i.hasNext()) {
                Element courant = (Element) i.next();

                config.put(courant.getName(), courant.getText());

            }

        } catch (JDOMException | IOException e) {

            Popup.showErrorAlert("!! Error !!", "Loading Config Error", e);

            System.exit(1);
        }


    }

    public static ConfigLoader getInstance() {

        if (ourInstance == null)
            ourInstance = new ConfigLoader();

        return ourInstance;
    }

    public HashMap<String, String> getConfig() {
        return config;
    }

}
