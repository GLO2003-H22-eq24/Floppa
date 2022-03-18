package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.List;

public class ConditionSellerAssembleur {
	public ConditionSellerDto toDto(String sellerId, String productTitle, List<String> productCategories, Double productMinPrice, Double productMaxPrice) throws ErrorException {
		ConditionProductDto conditionProductDto = new ConditionProductDtoBuilder()
				.addTitle(productTitle)
				.addProductCategories(ProductCategory.toEnum(productCategories))
				.addMinPrice(productMinPrice).addMaxPrice(productMaxPrice).build();
		return new ConditionSellerDto(conditionProductDto, sellerId);
	}

	public ConditionSellerDto toDto(ConditionProductDto conditionProductDto) {
		return new ConditionSellerDto(conditionProductDto, null);
	}
}
