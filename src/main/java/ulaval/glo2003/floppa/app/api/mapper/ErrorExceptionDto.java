package ulaval.glo2003.floppa.app.api.mapper;

public class ErrorExceptionDto {
	private String code;
	private String description;

	public ErrorExceptionDto(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public String getDescription() {
		return description;
	}
}
