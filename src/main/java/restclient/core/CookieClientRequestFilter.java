package restclient.core;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.ext.Provider;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bastiangardel on 19.07.17.
 */
@Provider
public class CookieClientRequestFilter implements ClientRequestFilter {
    private Cookie cookie;

    public CookieClientRequestFilter(Cookie cookie) {
        super();
        this.cookie = cookie;
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext) {
        List<Object> cookies = new ArrayList<>();
        cookies.add(this.cookie);
        clientRequestContext.getHeaders().put("Cookie", cookies);
    }
}
