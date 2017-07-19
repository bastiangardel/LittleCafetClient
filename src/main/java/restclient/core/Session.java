package restclient.core;

import javax.ws.rs.core.NewCookie;
import java.util.Map;

/**
 * Created by bastiangardel on 19.07.17.
 */
public class Session {

    private Map<String,NewCookie> cookies;

    private static Session ourInstance;

    public static Session getInstance() {


        if (ourInstance == null)
            ourInstance = new Session();

        return ourInstance;
    }

    private Session() {
    }

    public Map<String, NewCookie> getCookies() {
        return cookies;
    }

    public void open(Map<String, NewCookie> cookies){
        this.cookies = cookies;
    }

    public void close(){
        cookies = null;
    }
}
