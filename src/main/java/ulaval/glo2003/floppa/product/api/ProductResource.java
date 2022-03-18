package ulaval.glo2003.floppa.product.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.javatuples.Pair;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Path("/products")
public class ProductResource {
	private ProductService productService;
	private ProductAssembler productAssembler;
	private ConditionSellerAssembleur conditionSellerAssembleur;

	@Inject
	public ProductResource(ProductService productService, ProductAssembler productAssembler, ConditionSellerAssembleur conditionSellerAssembleur) {
		this.productService = productService;
		this.productAssembler = productAssembler;
		this.conditionSellerAssembleur = conditionSellerAssembleur;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(ProductCreationDtoRequest productCreationDtoRequest, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws ErrorException {
		String sellerId = retrieveSellerIdFromHeaders(headers);
		Product product = this.productAssembler.fromDto(productCreationDtoRequest);
		this.productService.createProductForSeller(sellerId, product);
		return Response.status(Response.Status.CREATED).location(URI.create(uriInfo.getBaseUri() + "products/" + product.getId())).build();
	}

	@GET
	@Path("/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveProduct(@PathParam("productId") String id) throws ErrorException {
		Product product = this.productService.retrieveOneProductWithConditions(new ConditionProductDtoBuilder().addProductId(id).build());
		Seller seller = this.productService.retrieveSellerByProduct(product);
		ProductDtoResponse productWithSellerDtoResponse = productAssembler.toDto(product, seller);
		return Response.ok().entity(productWithSellerDtoResponse).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveProductWithFilter(@QueryParam("sellerId") String sellerId,
	                                          @QueryParam("title") String productTitle,
	                                          @QueryParam("categories") List<String> productCategories,
	                                          @QueryParam("minPrice") Double productMinPrice,
	                                          @QueryParam("maxPrice") Double productMaxPrice) throws ErrorException {
		ConditionSellerDto conditionSellerDtos = conditionSellerAssembleur.toDto(sellerId, productTitle, productCategories, productMinPrice, productMaxPrice);
		List<Pair<Seller, Product>> productsBySeller = this.productService.retrieveProductBySellerWithConditions(conditionSellerDtos);
		List<ProductDtoResponse> productDtoResponses = productAssembler.toDto(productsBySeller);
		return Response.ok().entity(productDtoResponses).build();
	}

	private String retrieveSellerIdFromHeaders(HttpHeaders headers) throws ErrorException {
		return Optional.ofNullable(headers.getRequestHeaders().getFirst("X-Seller-Id")).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
	}
}
