package ulaval.glo2003.floppa.product.api;

import org.javatuples.Pair;
import ulaval.glo2003.floppa.app.domain.DateUtil;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.offers.api.response.OffersResponse;
import ulaval.glo2003.floppa.product.api.request.ProductCreationRequest;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;
import ulaval.glo2003.floppa.product.api.response.ProductViewResponse;
import ulaval.glo2003.floppa.product.applicative.ProductDto;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class ProductAssembler {
	private final OffersAssembler offersAssembler;

	public ProductAssembler(OffersAssembler offersAssembler) {
		this.offersAssembler = offersAssembler;
	}

	public ProductResponse toResponse(Product product, boolean detailledItems){
		OffersResponse offersResponse = offersAssembler.toResponse(product, detailledItems);
		Double amount = BigDecimal.valueOf(product.getSuggestedPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		List<String> productCategories = product.getCategories().stream().map(ProductCategory::toValueLowerCase).collect(Collectors.toList());
		return new ProductResponse(product.getId(), DateUtil.toISO8601WithHours(product.getCreatedDate()),
				product.getTitle(), product.getDescription(), amount, offersResponse, productCategories);
	}

	public ProductResponse toResponse(Product product, Seller seller, boolean detailledItems) {
		ProductResponse productResponse = this.toResponse(product, detailledItems);
		SellerResponse sellerResponse = new SellerResponse(seller.getId(), seller.getName());
		productResponse.setSellerDtoResponse(sellerResponse);
		return productResponse;
	}

	public List<ProductResponse> toResponse(List<Pair<Seller, Product>> pairsSellerProduct,boolean detailledItems) {
		return pairsSellerProduct.stream().map(pairSellerProduct -> this.toResponse(pairSellerProduct.getValue1(), pairSellerProduct.getValue0(),detailledItems)).collect(Collectors.toList());
	}

	public ProductDto toDto(ProductCreationRequest productCreationRequest) throws ErrorException {
		assertNotNullParams(productCreationRequest);
		assertNotBlankParams(productCreationRequest);
		List<ProductCategory> productCategories = new ArrayList<>();
		extractProductCategories(productCreationRequest, productCategories);
		return new ProductDto(productCreationRequest.getTitle(), productCreationRequest.getDescription(), productCreationRequest.getSuggestedPrice(), productCategories);
	}

	private void assertNotBlankParams(ProductCreationRequest productCreationRequest) throws ErrorException {
		if (productCreationRequest.getTitle().isBlank() || productCreationRequest.getDescription().isBlank() ) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}

	private void assertNotNullParams(ProductCreationRequest productCreationRequest) throws ErrorException {
		if (isNull(productCreationRequest.getTitle()) || isNull(productCreationRequest.getDescription())  || isNull(productCreationRequest.getSuggestedPrice()) ) {
			throw new ErrorException(ErrorCode.MISSING_PARAMETER);
		}
	}

	private void extractProductCategories(ProductCreationRequest productCreationRequest, List<ProductCategory> productCategories) throws ErrorException {
		if (!isNull(productCreationRequest.getCategories())){
			for (String categoryString : productCreationRequest.getCategories()) {
				productCategories.add(ProductCategory.toEnum(categoryString));
			}
		}
	}

	public List<ProductViewResponse> toResponseViews(List<Product> products) {
		return new ArrayList<>(); //TODO: mettre chaque product dans un ProductViewResponse
	}
}
