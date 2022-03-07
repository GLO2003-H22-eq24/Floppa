package ulaval.glo2003.floppa.app.domain;


public class ErrorException extends Exception{
	private final String code;
	private final String description;

	public ErrorException(ErrorCode errorCode) {
		this.code = errorCode.name();
		this.description = errorCode.getDescription();
	}
	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
