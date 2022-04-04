package ulaval.glo2003.floppa.app.api.mapper;

import ulaval.glo2003.floppa.app.domain.ErrorException;

public class ErrorExceptionAssembler {

	public ErrorExceptionResponse toDto(ErrorException errorException) {
		return new ErrorExceptionResponse(errorException.getCode(), errorException.getDescription());
	}
}
