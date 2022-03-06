package ulaval.glo2003.floppa.product.api;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;

@Path("/products")
public class ProductResource {

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(ProductCreationDtoRequest productCreationDtoRequest, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws ErrorException {
		//TODO: faire les call au repo
		return null;
	}


}
