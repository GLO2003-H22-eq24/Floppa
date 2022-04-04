package ulaval.glo2003.floppa.app.config.dto;

public class HttpConfigDto {
	private final int port;

	public HttpConfigDto(int port) {
		this.port = port;
	}

	public int getPort() {
		return port;
	}
}
