package ulaval.glo2003.floppa.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
class ProductTest {

	@Test
	void givenLessThan1Price_whenCreateProduct_thenINVALID_PARAMETER() {
		Double price = 0.0;

		Assertions.assertThrows(ErrorException.class, () -> new Product("anyTitle", "anyDescription", price, new ArrayList<>()));
	}

	@Test
	void givenProductWithNoOffer_whenComputeMeanOffers_thenNull() throws ErrorException {
		Product product = new Product("anyTitle", "anyDescription", 10., new ArrayList<>());

		Double meanOffer = product.computeMeanOffers();

		Assertions.assertNull(meanOffer);
	}

	@Test
	void givenProductWithOffers_whenComputeMeanOffers_thenMeanOffer() throws ErrorException {
		Product product = new Product("anyTitle", "anyDescription", 30., new ArrayList<>());
		product.getOffers().add(new Offers(10.));

		Double meanOffer = product.computeMeanOffers();

		Assertions.assertEquals(10.,meanOffer);
	}
}