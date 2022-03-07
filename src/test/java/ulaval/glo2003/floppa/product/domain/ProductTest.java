package ulaval.glo2003.floppa.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductTest {

	@Test
	void givenLessThan1Price_whenCreateProduct_thenINVALID_PARAMETER() {
		Double price = 0.0;

		Assertions.assertThrows(ErrorException.class, () -> new Product("anyTitle", "anyDescription", price, new ArrayList<>()));
	}
}