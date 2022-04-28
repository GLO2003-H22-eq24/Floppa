package ulaval.glo2003.floppa.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ulaval.glo2003.floppa.product.domain.ProductFactory.STARTING_VIEWS;

@ExtendWith(MockitoExtension.class)
class ProductTest {
	private Product anyProduct;

	@BeforeEach
	void givenAnyProduct() {
		String name = "name";
		String desc = "desc";
		String id = "id";
		Double suggestedPrice = 1.2;
		anyProduct = new Product(name, desc, suggestedPrice, List.of(ProductCategory.APPAREL), id, LocalDateTime.now(), STARTING_VIEWS);
	}

	@Test
	void givenProductWithOffers_whenComputeMeanOffers_thenMeanOffers() throws ErrorException {
		Double price1 = 20.;
		Offers offers1 = mockOffers(price1);
		Double price2 = 10.;
		Offers offers2 = mockOffers(price2);
		anyProduct.addOffer(offers1);
		anyProduct.addOffer(offers2);

		Double meanOffers = anyProduct.computeMeanOffers();

		Assertions.assertEquals(15., meanOffers);
	}

	@Test
	void givenProductWithNoOffers_whenComputeMeanOffers_thenNull() {

		Double meanOffers = anyProduct.computeMeanOffers();

		assertNull(meanOffers);
	}

	@Test
	void givenOffers_whenComputeNumberOfOffers_thenNumberOfOffers() throws ErrorException {
		Offers offers1 = mockOffers(20.);
		Offers offers2 = mockOffers(20.);
		anyProduct.addOffer(offers1);
		anyProduct.addOffer(offers2);

		int nbOffers = anyProduct.computeNumberOfOffers();

		Assertions.assertEquals(2, nbOffers);
	}

	@Test
	void givenBigOffers_whenComputeMaxOffers_thenBigOffers() throws ErrorException {
		Double bigPrice = 20.;
		Offers bigOffers = mockOffers(bigPrice);
		Offers offers2 = mockOffers(10.);
		anyProduct.addOffer(bigOffers);
		anyProduct.addOffer(offers2);

		Double maxOffers = anyProduct.computeMaxOffers();

		Assertions.assertEquals(bigPrice, maxOffers);
	}

	@Test
	void givenSmallOffers_whenComputeMinOffers_thenSmallOffers() throws ErrorException {
		Double smallPrice = 10.;
		Offers smallOffers = mockOffers(smallPrice);
		Offers offers2 = mockOffers(20.);
		anyProduct.addOffer(smallOffers);
		anyProduct.addOffer(offers2);

		Double minOffers = anyProduct.computeMinOffers();

		Assertions.assertEquals(smallPrice, minOffers);
	}

	@Test
	void givenProduct_whenAddViews_thenIncrementViews() {
		Integer views = anyProduct.getViews();

		anyProduct.addView();

		Assertions.assertEquals(views + 1, anyProduct.getViews());
	}

	private Offers mockOffers(Double price) {
		Offers offers = Mockito.mock(Offers.class);
		Mockito.when(offers.getOfferAmount()).thenReturn(price);
		return offers;
	}
}