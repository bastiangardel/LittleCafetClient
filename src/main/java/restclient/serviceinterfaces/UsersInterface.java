package restclient.serviceinterfaces;

import restclient.dto.CredentialDTO;
import restclient.dto.UserInfoDTO;

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

    @GET
    @Path("/info")
    @Produces({ MediaType.APPLICATION_JSON })
    UserInfoDTO getInfo();


}
