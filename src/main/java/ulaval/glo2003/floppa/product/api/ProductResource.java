package ulaval.glo2003.floppa.product.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import org.javatuples.Pair;
import ulaval.glo2003.floppa.app.api.HttpParamUtil;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.offers.api.request.OffersRequest;
import ulaval.glo2003.floppa.offers.applicative.OffersDto;
import ulaval.glo2003.floppa.offers.applicative.OffersService;
import ulaval.glo2003.floppa.product.api.request.ProductCreationRequest;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;
import ulaval.glo2003.floppa.product.api.response.ProductViewResponse;
import ulaval.glo2003.floppa.product.applicative.ProductDto;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;
import java.util.List;

@Path("/products")
public class ProductResource {
	private ProductService productService;
	private OffersService offersService;
	private ProductAssembler productAssembler;
	private ConditionSellerAssembleur conditionSellerAssembleur;
	private OffersAssembler offersAssembler;
	@Inject
	public ProductResource(ProductService productService, ProductAssembler productAssembler, ConditionSellerAssembleur conditionSellerAssembleur, OffersAssembler offersAssembler, OffersService offersService) {
		this.productService = productService;
		this.productAssembler = productAssembler;
		this.conditionSellerAssembleur = conditionSellerAssembleur;
		this.offersAssembler = offersAssembler;
		this.offersService = offersService;
	}

	@POST
	@Path("/{productId}/offers")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createOffer(OffersRequest offersRequest, @PathParam("productId") String productId) throws ErrorException {
		OffersDto offersDto = offersAssembler.toDto(offersRequest);
		offersService.addOfferToProduct(HttpParamUtil.fetchId(productId), offersDto);
		return Response.ok().build();
	}

	@POST
	@Path("/{productId}/views")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addView(@PathParam("productId") String productId) throws ErrorException {
		this.productService.addViewToProduct(HttpParamUtil.fetchId(productId));
		return Response.ok().build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createProduct(ProductCreationRequest productCreationRequest, @Context UriInfo uriInfo, @Context HttpHeaders headers) throws ErrorException {
		String sellerId = HttpParamUtil.retrieveSellerIdFromHeaders(headers);
		ProductDto productDto = this.productAssembler.toDto(productCreationRequest);
		Product product = this.productService.createProductForSeller(sellerId, productDto);
		return Response.status(Response.Status.CREATED).location(URI.create(uriInfo.getBaseUri() + "products/" + product.getId())).build();
	}

	@GET
	@Path("/{productId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveProduct(@PathParam("productId") String id, @Context HttpHeaders httpheaders) throws ErrorException {
		if (id.equals("@me") || id.equals("%40me")) {
			return retrieveMeProducts(httpheaders);
		} else {
			Product product = this.productService.retrieveOneProductWithConditions(new ConditionProductDtoBuilder().addProductId(HttpParamUtil.fetchId(id)).build());
			Seller seller = this.productService.retrieveSellerByProduct(product);
			ProductResponse productWithSellerDtoResponse = productAssembler.toResponse(product, seller, false);
			return Response.ok().entity(productWithSellerDtoResponse).build();
		}
	}

	private Response retrieveMeProducts(HttpHeaders httpheaders) throws ErrorException {
		String sellerId = HttpParamUtil.retrieveSellerIdFromHeaders(httpheaders);
		List<Product> products = productService.retrieveOnlyProductBySellerWithConditions(conditionSellerAssembleur.toDto(sellerId));
		List<ProductViewResponse> productViewResponses = productAssembler.toResponseViews(products);
		return Response.ok().entity(productViewResponses).build();
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
		List<ProductResponse> productResponse = productAssembler.toResponse(productsBySeller, false);
		return Response.ok().entity(productResponse).build();
	}
}
