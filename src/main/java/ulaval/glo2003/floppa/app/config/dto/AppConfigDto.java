package ulaval.glo2003.floppa.app.config.dto;

import java.net.ConnectException;
import java.util.Optional;

public class AppConfigDto {
	private final DbConfigDto dbConfigDto;
	private final HttpConfigDto httpConfigDto;

	public AppConfigDto(DbConfigDto dbConfigDto, HttpConfigDto httpConfigDto) {
		this.dbConfigDto = dbConfigDto;
		this.httpConfigDto = httpConfigDto;
	}

	public AppConfigDto(HttpConfigDto httpConfigDto) {
		this.httpConfigDto = httpConfigDto;
		this.dbConfigDto = null;
	}

	public DbConfigDto getDbConfigDto() throws ConnectException {
		return Optional.ofNullable(dbConfigDto).orElseThrow(ConnectException::new);
	}

	public HttpConfigDto getHttpConfigDto() {
		return httpConfigDto;
	}
}
