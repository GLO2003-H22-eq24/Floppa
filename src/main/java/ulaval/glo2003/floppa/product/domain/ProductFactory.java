package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.applicative.ProductDto;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class ProductFactory {
	public Product createProduct(ProductDto productDto) throws ErrorException {
		this.validatePrice(productDto.getSuggestedPrice());
		return new Product(productDto.getTitle(), productDto.getDescription(), productDto.getSuggestedPrice(),
				productDto.getCategories(), UUID.randomUUID().toString(), LocalTime.now(Clock.system(ZoneOffset.UTC)));
	}

	private void validatePrice(Double suggestedPrice) throws ErrorException {
		if (suggestedPrice < 1) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}
}
