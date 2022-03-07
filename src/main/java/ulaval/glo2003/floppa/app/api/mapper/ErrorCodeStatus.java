package ulaval.glo2003.floppa.app.api.mapper;

import ulaval.glo2003.floppa.app.domain.ErrorCode;

import java.util.Arrays;

public enum ErrorCodeStatus {
	MISSING_PARAMETER(ErrorCode.MISSING_PARAMETER.name(), 400),
	INVALID_PARAMETER(ErrorCode.INVALID_PARAMETER.name(), 400),
	ITEM_NOT_FOUND(ErrorCode.ITEM_NOT_FOUND.name(), 404);

	private final String errorCodeName;
	private final int statusCode;

	ErrorCodeStatus(String errorCodeName, int statusCode) {
		this.errorCodeName = errorCodeName;
		this.statusCode = statusCode;
	}

	public String getErrorCodeName() {
		return errorCodeName;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public static ErrorCodeStatus mapToErrorStatus(String errorCodeName){
		return Arrays.stream(ErrorCodeStatus.values()).filter(errorCodeStatus -> errorCodeStatus.errorCodeName.equals(errorCodeName)).findFirst().orElseThrow();
	}
}
