package restclient.core;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;
import tools.ConfigLoader;

import javax.ws.rs.core.UriBuilder;

/**
 * Created by bastiangardel on 19.07.17.
 */
public class RestClient {

    private ResteasyClient client;
    private ResteasyWebTarget target;


    private static RestClient ourInstance;

    public static RestClient getInstance() {

        if (ourInstance == null)
            ourInstance = new RestClient();

        return ourInstance;
    }

    private RestClient() {

        client = new ResteasyClientBuilder().build();
        target = client.target(UriBuilder.fromPath(ConfigLoader.getInstance().getConfig().get("restapiurl")));

    }

    public ResteasyWebTarget getTarget() {
        return target;
    }

    public ResteasyClient getClient() {
        return client;
    }
}
