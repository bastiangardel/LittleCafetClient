package RestClient.serviceinterfaces;

import RestClient.dto.CredentialDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by bastiangardel on 18.07.17.
 */
@Path("/users")
public interface usersClient {

    @POST
    @Path("/auth")
    @Consumes({ MediaType.APPLICATION_JSON })
    void authenticate(CredentialDTO credentials);


    @POST
    @Path("/auth")
    void logout();


}
