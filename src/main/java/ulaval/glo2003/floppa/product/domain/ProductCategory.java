package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.ArrayList;
import java.util.List;

public enum ProductCategory {
	SPORTS,
	ELECTRONICS,
	APPAREL,
	BEAUTY,
	HOUSING,
	OTHER;

	public static ProductCategory toEnum(String name) throws ErrorException {
		try {
			return ProductCategory.valueOf(name.toUpperCase());
		}catch (IllegalArgumentException illegalArgumentException){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}

	public static List<ProductCategory> toEnum(List<String> productCategories) throws ErrorException {
		List<ProductCategory> productCategoriesEnum = new ArrayList<>();
		for (String category: productCategories){
			productCategoriesEnum.add(ProductCategory.toEnum(category));
		}
		return productCategoriesEnum;
	}

	public String toValueLowerCase(){
		return this.name().toLowerCase();
	}
}
