package ulaval.glo2003.floppa.product.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.product.domain.FilterBuilderProduct;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;
import ulaval.glo2003.floppa.seller.domain.FilterBuilderSeller;
import ulaval.glo2003.floppa.seller.domain.Seller;

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

	@GET
	@Path("/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveProduct(@PathParam("productId") String id) throws ErrorException {
		Product product = this.productService.retriveProductByConditions(new FilterBuilderSeller().build(), new FilterBuilderProduct().addProductIdCondition(id).build())
				.stream().findFirst().orElseThrow();
		Seller seller = this.productService.retriveSellerByProduct(product);

		//TODo implémenter les méthodes ->
		//SellerDtoResponse sellerDtoResponse = productAssembler.toDto(product, seller);
		//return Response.ok().entity(sellerDtoResponse).build();

		return null;
	}
}
