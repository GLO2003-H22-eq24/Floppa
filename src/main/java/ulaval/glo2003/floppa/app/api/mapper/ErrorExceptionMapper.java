package ulaval.glo2003.floppa.app.api.mapper;

import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ulaval.glo2003.floppa.app.domain.ErrorException;

@Provider
public class ErrorExceptionMapper implements ExceptionMapper<ErrorException> {
	@Override
	public Response toResponse(ErrorException errorException) {
		return Response.status(errorException.getStatusCode())
				.type(MediaType.APPLICATION_JSON)
				.entity(errorException)
				.build();
	}
}
