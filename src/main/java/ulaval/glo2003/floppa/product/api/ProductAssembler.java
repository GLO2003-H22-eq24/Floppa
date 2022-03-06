package ulaval.glo2003.floppa.product.api;

import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.offers.api.OffersDto;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ProductAssembler {
	private final OffersAssembler offersAssembler;

	public ProductAssembler(OffersAssembler offersAssembler) {
		this.offersAssembler = offersAssembler;
	}

	public ProductDto toDto(Product product){
		OffersDto offersDto = offersAssembler.toDto(product);
		Double amount = BigDecimal.valueOf(product.getSuggestedPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return new ProductDto(product.getId(), product.getCreatedDate(), product.getTitle(), product.getDescription(), amount, offersDto);
	}

	public Product fromDto(ProductCreationDtoRequest productCreationDtoRequest){
		//TODO: faire le product
		return null;
	}
}
