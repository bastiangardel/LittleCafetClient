package restclient.serviceinterfaces;

import restclient.core.Session;
import restclient.dto.CredentialDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bastiangardel on 18.07.17.
 */
@Path("/users")
public interface UsersInterface {

    @POST
    @Path("/auth")
    @Consumes({ MediaType.APPLICATION_JSON })
    Response authenticate(CredentialDTO credentials);


    @POST
    @Path("/logout")
    Response logout();


}
