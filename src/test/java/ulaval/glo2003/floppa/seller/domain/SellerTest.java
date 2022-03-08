package ulaval.glo2003.floppa.seller.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
class SellerTest {
	private String anyName="anyName";
	private String anyDescription="anyDescription";

	@Test
	void givenLessThan18Age_whenCreateSeller_thenINVALID_PARAMETER() {
		LocalDate birthDateLessThan18 = LocalDate.now();

		Assertions.assertThrows(ErrorException.class, () -> new Seller(anyName, anyDescription, birthDateLessThan18));
	}

}