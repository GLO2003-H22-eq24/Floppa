package ulaval.glo2003.floppa.seller.applicative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerFactory;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SellerServiceTest {
	@Mock
	private SellerFactory sellerFactory;
	@Mock
	private SellerRepository sellerRepository;
	@InjectMocks
	private SellerService sellerService;
	@Mock
	private SellerDto sellerDto;
	@Mock
	private Seller anySeller;

	@Test
	void givenSellerDto_whenAddSeller_thenSaveSeller() throws ErrorException {
		Mockito.when(sellerFactory.createSeller(sellerDto)).thenReturn(anySeller);

		sellerService.addSeller(sellerDto);

		Mockito.verify(sellerRepository, Mockito.times(1)).saveSeller(anySeller);
	}

	@Test
	void givenSellerDto_whenAddSeller_thenSeller() throws ErrorException {
		Mockito.when(sellerFactory.createSeller(sellerDto)).thenReturn(anySeller);

		Seller seller = sellerService.addSeller(sellerDto);

		Assertions.assertEquals(anySeller, seller);
	}

	@Test
	void givenSellerId_whenRetrieveSeller_thenRetrieveSellerFromRepository() throws ErrorException {
		String anyId = "id";

		sellerService.retrieveSeller(anyId);

		Mockito.verify(sellerRepository, Mockito.times(1)).retrieveSeller(anyId);
	}
}