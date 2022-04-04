package ulaval.glo2003.floppa.product.domain;

import com.google.common.base.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.applicative.ProductDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductFactoryTest {
	private final ProductFactory productFactory = new ProductFactory();

	@Test
	void givenSuggestedPriceLessThan1_whenCreateProduct_thenErrorException() {
		Double suggestedPriceLessThan1 = 0.6;
		ProductDto productDto = Mockito.mock(ProductDto.class);
		Mockito.when(productDto.getSuggestedPrice()).thenReturn(suggestedPriceLessThan1);

		Executable productSupplier = () -> productFactory.createProduct(productDto);

		Assertions.assertThrows(ErrorException.class, productSupplier);
	}
}