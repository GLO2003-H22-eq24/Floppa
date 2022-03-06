package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

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
	public String toValueLowerCase(){
		return this.name().toLowerCase();
	}
}
