package restclient.serviceinterfaces;

import restclient.dto.CredentialDTO;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
