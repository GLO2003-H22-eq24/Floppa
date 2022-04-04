package ulaval.glo2003.floppa.offers.applicative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;
import ulaval.glo2003.floppa.offers.domain.OffersFactory;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class OffersServiceTest {
	@Mock
	private SellerRepository sellerRepository;
	@Mock
	private OffersFactory offersFactory;
	@Mock
	private ConditionSellerAssembleur conditionSellerAssembleur;
	@InjectMocks
	private OffersService offersService;
	@Mock
	private OffersDto anyOffersDto;
	private final String anyProductId ="id";

	@Test
	void givenProductIdAndOffersDto_whenAddOfferToProduct_thenUpdateProductWithOffer() throws ErrorException {
		Offers offers = Mockito.mock(Offers.class);
		Product product = Mockito.mock(Product.class);
		Mockito.when(offersFactory.createOffers(anyOffersDto)).thenReturn(offers);
		Mockito.when(sellerRepository.findProducts(Mockito.any())).thenReturn(List.of(product));

		offersService.addOfferToProduct(anyProductId, anyOffersDto);

		Mockito.verify(sellerRepository, Mockito.times(1)).updateProduct(product);
	}

	@Test
	void givenInvalidProductIdAndOffersDto_whenAddOfferToProduct_thenErrorException() throws ErrorException {
		Offers offers = Mockito.mock(Offers.class);
		Mockito.when(offersFactory.createOffers(anyOffersDto)).thenReturn(offers);
		Mockito.when(sellerRepository.findProducts(Mockito.any())).thenReturn(new ArrayList<>());

		Executable addOfferToProduct = () -> offersService.addOfferToProduct(anyProductId, anyOffersDto);

		Assertions.assertThrows(ErrorException.class, addOfferToProduct);
	}
}
