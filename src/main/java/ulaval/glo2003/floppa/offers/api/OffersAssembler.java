package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OffersAssembler {
	public OffersDto toDto(Product product){
		Double amount = BigDecimal.valueOf(product.computeMeanOffers()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return new OffersDto(amount, product.computeNumberOfOffers());
	}
}
