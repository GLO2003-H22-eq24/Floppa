package ulaval.glo2003.floppa.offers.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;

@ExtendWith(MockitoExtension.class)
class PhoneNumberTest {

	@Test
	void givenPhoneNumberWithSeparator_whenPhoneNumber_thenNotThrow() {
		String anyPhoneWithSeparator = "418-245-5654";

		Executable newPhoneNumber = () -> new PhoneNumber(anyPhoneWithSeparator);

		Assertions.assertDoesNotThrow(newPhoneNumber);
	}

	@Test
	void givenPhoneNumberWithNoSeparator_whenPhoneNumber_thenNotThrow() {
		String anyPhoneWithNoSeparator = "4182455654";

		Executable newPhoneNumber = () -> new PhoneNumber(anyPhoneWithNoSeparator);

		Assertions.assertDoesNotThrow(newPhoneNumber);
	}

	@Test
	void givenPhoneNumberWithRegionalIndication_whenPhoneNumber_thenNotThrow() {
		String anyPhoneWithRegionalIndication = "+14182455654";

		Executable newPhoneNumber = () -> new PhoneNumber(anyPhoneWithRegionalIndication);

		Assertions.assertDoesNotThrow(newPhoneNumber);
	}

	@Test
	void givenPhoneNumberMissingValue_whenPhoneNumber_thenNotThrow() {
		String anyPhoneWithMissingValue = "+1-418-245-565";

		Executable newPhoneNumber = () -> new PhoneNumber(anyPhoneWithMissingValue);

		Assertions.assertThrows(ErrorException.class, newPhoneNumber);
	}
}