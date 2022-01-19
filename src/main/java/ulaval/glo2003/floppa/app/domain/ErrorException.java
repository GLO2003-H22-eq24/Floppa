package ulaval.glo2003.floppa.app.domain;

import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTransient;

public class ErrorException extends Exception{
	@JsonbProperty("code")
	private String code;
	@JsonbProperty("description")
	private String description;
	@JsonbTransient
	private int statusCode;

	public ErrorException() {
	}

	public ErrorException(ErrorCode errorCode) {
		this.code = errorCode.name();
		this.description = errorCode.getDescription();
		this.statusCode = errorCode.getStatusCode();
	}
	public String getCode() {
		return code;
	}


	public String getDescription() {
		return description;
	}

	public int getStatusCode() {
		return statusCode;
	}
}
