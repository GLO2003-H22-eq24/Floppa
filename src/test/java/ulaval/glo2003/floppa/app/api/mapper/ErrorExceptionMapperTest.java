package ulaval.glo2003.floppa.app.api.mapper;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

@ExtendWith(MockitoExtension.class)
class ErrorExceptionMapperTest {
	private ErrorExceptionMapper errorExceptionMapper = new ErrorExceptionMapper(new ErrorExceptionAssembler());

	@Test
	void givenErrorException_whenToResponse_thenResponseWithErrorExceptionDto() {
		ErrorException errorException = new ErrorException(ErrorCode.MISSING_PARAMETER);

		Response response = errorExceptionMapper.toResponse(errorException);

		Assertions.assertEquals(ErrorExceptionResponse.class, response.getEntity().getClass());
	}
}