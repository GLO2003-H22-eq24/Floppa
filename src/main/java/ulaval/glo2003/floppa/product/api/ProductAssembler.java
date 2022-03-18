package ulaval.glo2003.floppa.product.api;

import org.javatuples.Pair;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.offers.api.message.OffersDto;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;
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

	public ProductDtoResponse toDto(Product product){
		OffersDto offersDto = offersAssembler.toDto(product);
		Double amount = BigDecimal.valueOf(product.getSuggestedPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		List<String> productCategories = product.getCategories().stream().map(ProductCategory::toValueLowerCase).collect(Collectors.toList());
		return new ProductDtoResponse(product.getId(), product.getCreatedDate(),
				product.getTitle(), product.getDescription(), amount,offersDto, productCategories);
	}

	public ProductDtoResponse toDto(Product product, Seller seller) {
		ProductDtoResponse productDtoResponse = this.toDto(product);
		SellerDtoResponse sellerDtoResponse = new SellerDtoResponse(seller.getId(), seller.getName());
		productDtoResponse.setSellerDtoResponse(sellerDtoResponse);
		return productDtoResponse;
	}

	public List<ProductDtoResponse> toDto(List<Pair<Seller, Product>> pairsSellerProduct) {
		return pairsSellerProduct.stream().map(pairSellerProduct -> this.toDto(pairSellerProduct.getValue1(), pairSellerProduct.getValue0())).collect(Collectors.toList());
	}

	public Product fromDto(ProductCreationDtoRequest productCreationDtoRequest) throws ErrorException {

		// Vérifie que les paramètres nécessaires sont présent
		if (isNull(productCreationDtoRequest.getTitle()) || isNull(productCreationDtoRequest.getDescription())  || isNull(productCreationDtoRequest.getSuggestedPrice()) ) {
			throw new ErrorException(ErrorCode.MISSING_PARAMETER);
		}
		// Vérifie que les paramètres nécessaires contiennent une valeur
		if (productCreationDtoRequest.getTitle().isBlank() || productCreationDtoRequest.getDescription().isBlank() ) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}

		List<ProductCategory> productCategories = new ArrayList<>();
		extractProductCategories(productCreationDtoRequest, productCategories);


		return new Product(productCreationDtoRequest.getTitle(), productCreationDtoRequest.getDescription(), productCreationDtoRequest.getSuggestedPrice(), productCategories);
	}

	private void extractProductCategories(ProductCreationDtoRequest productCreationDtoRequest, List<ProductCategory> productCategories) throws ErrorException {
		if (!isNull(productCreationDtoRequest.getCategories())){
			for (String categoryString : productCreationDtoRequest.getCategories()) {
				productCategories.add(ProductCategory.toEnum(categoryString));
			}
		}
	}
}
