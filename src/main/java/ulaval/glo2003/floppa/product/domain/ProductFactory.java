package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.app.domain.DateUtil;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.applicative.ProductDto;

import java.util.UUID;

public class ProductFactory {
	public static final int STARTING_VIEWS = 0;
	public Product createProduct(ProductDto productDto) throws ErrorException {
		this.validatePrice(productDto.getSuggestedPrice());
		return new Product(productDto.getTitle(), productDto.getDescription(), productDto.getSuggestedPrice(),
				productDto.getCategories(), UUID.randomUUID().toString(), DateUtil.getUtcNow(), STARTING_VIEWS);
	}

	private void validatePrice(Double suggestedPrice) throws ErrorException {
		if (suggestedPrice < 1) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}
}
