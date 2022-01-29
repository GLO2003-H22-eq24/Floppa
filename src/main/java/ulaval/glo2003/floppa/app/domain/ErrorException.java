package ulaval.glo2003.floppa.app.domain;


public class ErrorException extends Exception{
	private final String code;
	private final String description;
	private final int statusCode;

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
