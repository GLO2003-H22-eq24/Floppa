package ulaval.glo2003.floppa.product.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.product.domain.Product;

import java.net.URI;
import java.util.Optional;

@Path("/products")
public class ProductResource {
	private ProductService productService;
	private ProductAssembler productAssembler;

	@Inject
	public ProductResource(ProductService productService, ProductAssembler productAssembler) {
		this.productService = productService;
		this.productAssembler = productAssembler;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(ProductCreationDtoRequest productCreationDtoRequest, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws ErrorException {
		String sellerId = getSellerId(headers);
		Product product = this.productAssembler.fromDto(productCreationDtoRequest);
		this.productService.createProductForSeller(sellerId, product);
		return Response.status(Response.Status.CREATED).location(URI.create(uriInfo.getBaseUri() + "products/" + product.getId())).build();
	}

	private String getSellerId(HttpHeaders headers) throws ErrorException {
		return Optional.ofNullable(headers.getRequestHeaders().getFirst("X-Seller-Id")).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
	}
}
