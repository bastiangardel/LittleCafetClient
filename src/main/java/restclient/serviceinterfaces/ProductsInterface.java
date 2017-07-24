package restclient.serviceinterfaces;

import restclient.dto.CredentialDTO;
import restclient.dto.Product;
import restclient.dto.UserInfoDTO;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by bastiangardel on 18.07.17.
 */
@Path("/products")
public interface ProductsInterface {

    @GET
    @Produces({ MediaType.APPLICATION_JSON })
    List<Product> getProducts();


}
