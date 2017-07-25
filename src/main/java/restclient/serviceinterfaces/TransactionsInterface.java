package restclient.serviceinterfaces;

import restclient.dto.CredentialDTO;
import restclient.dto.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by bastiangardel on 18.07.17.
 */
@Path("/transaction")
public interface TransactionsInterface {

    @POST
    @Path("/buy")
    @Consumes({MediaType.APPLICATION_JSON})
    Response buy(List<Product> productsList);
}
