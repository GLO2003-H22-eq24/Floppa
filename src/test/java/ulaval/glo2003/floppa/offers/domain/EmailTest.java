package ulaval.glo2003.floppa.offers.domain;

import com.google.common.base.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmailTest {

	@Test
	void givenInvalidEmailFormat_whenNewEmail_thenErrorException() {
		String invalidEmail = "asdfre@@@";

		Assertions.assertThrows(ErrorException.class, () -> new Email(invalidEmail));
	}

	@Test
	void givenEmailFormat_whenNewEmail_thenNoErrorException() {
		String invalidEmail = "asdfre@greinegr.com";

		Assertions.assertDoesNotThrow(() -> new Email(invalidEmail));
	}
}