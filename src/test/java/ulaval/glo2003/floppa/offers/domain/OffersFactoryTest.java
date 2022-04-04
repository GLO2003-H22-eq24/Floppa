package ulaval.glo2003.floppa.offers.domain;

import com.google.common.base.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.applicative.OffersDto;

@ExtendWith(MockitoExtension.class)
class OffersFactoryTest {

	private final OffersFactory offersFactory = new OffersFactory();

	@Test
	void givenOffersDtoWithMessageLengthLessThan100_whenCreateOffers_thenErrorException() throws ErrorException {
		String anyName = "name";
		String anyEmail = "email@asd.com";
		String anyPhone = "4181233333";
		Double amount = 12.;
		String messageLengthLessThan100 = "asd";
		OffersDto offersDto = new OffersDto(anyName, new Email(anyEmail), new PhoneNumber(anyPhone), amount, messageLengthLessThan100);

		Assertions.assertThrows(ErrorException.class, () -> offersFactory.createOffers(offersDto));
	}
}