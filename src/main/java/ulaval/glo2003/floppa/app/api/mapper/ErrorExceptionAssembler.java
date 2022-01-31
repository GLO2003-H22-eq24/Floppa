package ulaval.glo2003.floppa.app.api.mapper;

import ulaval.glo2003.floppa.app.domain.ErrorException;

public class ErrorExceptionAssembler {

	public ErrorExceptionDto toDto(ErrorException errorException) {
		return new ErrorExceptionDto(errorException.getCode(), errorException.getDescription());
	}
}
