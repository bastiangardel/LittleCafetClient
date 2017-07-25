package restclient.serviceinterfaces;

import restclient.dto.Product;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * Created by bastiangardel on 18.07.17.
 */
@Path("/products")
public interface ProductsInterface {

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    List<Product> getProducts();


}
