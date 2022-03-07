package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class OffersAssembler {
	public OffersDto toDto(Product product){
		Double amount = Optional.ofNullable(product.computeMeanOffers()).map(mean -> BigDecimal.valueOf(mean).setScale(2, RoundingMode.HALF_UP).doubleValue()).orElse(null);
		return new OffersDto(amount, product.computeNumberOfOffers());
	}
}
