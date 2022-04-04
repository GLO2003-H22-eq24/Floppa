package ulaval.glo2003.floppa.app.api.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorCode;

@ExtendWith(MockitoExtension.class)
class ErrorCodeStatusTest {
	@Test
	void givenErrorCodeName_whenMapToErrorStatus_thenErrorCodeStatus() {
		String errorCodeName = ErrorCode.INVALID_PARAMETER.name();

		ErrorCodeStatus errorCodeStatus = ErrorCodeStatus.mapToErrorStatus(errorCodeName);

		Assertions.assertEquals(errorCodeStatus.getErrorCodeName(), errorCodeName);
	}

	@Test
	void givenNotExistingErrorCodeName_whenMapToErrorStatus_thenException() {
		String errorCodeName = "NotExistingErrorCodeName";

		Assertions.assertThrows(Exception.class, () -> ErrorCodeStatus.mapToErrorStatus(errorCodeName));
	}
}
