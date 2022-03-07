package ulaval.glo2003.floppa.app.api.mapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ulaval.glo2003.floppa.app.domain.ErrorException;

@Provider
public class ErrorExceptionMapper implements ExceptionMapper<ErrorException> {
	private ErrorExceptionAssembler errorExceptionAssembler;
	@Inject
	public ErrorExceptionMapper(ErrorExceptionAssembler errorExceptionAssembler) {
		this.errorExceptionAssembler = errorExceptionAssembler;
	}

	@Override
	public Response toResponse(ErrorException errorException) {
		return Response.status(ErrorCodeStatus.mapToErrorStatus(errorException.getCode()).getStatusCode())
				.type(MediaType.APPLICATION_JSON)
				.entity(errorExceptionAssembler.toDto(errorException))
				.build();
	}
}
