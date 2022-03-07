package ulaval.glo2003.floppa.product.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.product.domain.FilterBuilderProduct;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;
import ulaval.glo2003.floppa.seller.domain.FilterBuilderSeller;
import ulaval.glo2003.floppa.product.applicative.ConditionBuilderProduct;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;
import ulaval.glo2003.floppa.seller.applicative.ConditionBuilderSeller;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;
import java.util.List;
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
		Product product = this.productService.retrieveProductByConditions(new FilterBuilderSeller().build(), new FilterBuilderProduct().addProductIdCondition(id).build());
		//Product product = this.productService.retrieveProductByConditions(new ConditionBuilderSeller().build(), new ConditionBuilderProduct().addProductIdCondition(id).build());
    
		Seller seller = this.productService.retrieveSellerByProduct(product);
		ProductDtoResponse productWithSellerDtoResponse = productAssembler.toDto(product, seller);
		return Response.ok().entity(productWithSellerDtoResponse).build();
	}

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response retrieveProductWithFilter(@QueryParam("sellerId") String sellerId,
	                                          @QueryParam("title") String title,
	                                          @QueryParam("categories") List<String> productCategories,
	                                          @QueryParam("minPrice") Double minPrice,
	                                          @QueryParam("maxPrice") Double maxPrice) throws ErrorException {

		Product product = this.productService.retrieveProductByConditions(new FilterBuilderSeller()
						.addSellerIdCondition(sellerId)
						.build(),
				new FilterBuilderProduct()

		//Product product = this.productService.retrieveProductByConditions(new ConditionBuilderSeller()
		//				.addSellerIdCondition(sellerId)
		//				.build(),
		//		new ConditionBuilderProduct()
		//				.addProductTitleCondition(title)
		//				.addCategoriesCondition(ProductCategory.toEnum(productCategories))
		//				.addMinPriceCondition(minPrice)
		//				.addMaxPriceCondition(maxPrice)
		//				.build());
		Seller seller = this.productService.retrieveSellerByProduct(product);
		ProductDtoResponse productWithSellerDtoResponse = productAssembler.toDto(product, seller);
		return Response.ok().entity(productWithSellerDtoResponse).build();
	}
}
