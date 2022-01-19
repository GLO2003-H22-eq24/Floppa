package ulaval.glo2003.floppa.app.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class HttpServerFactoryTestIT {

	private HttpServerFactory httpServerFactory;
	private final ResourceConfig anyResourceConfig = new ResourceConfig();

	@BeforeEach
	void givenHttpServerFactoryWithAnyResourceConfig() {
		this.httpServerFactory = new HttpServerFactory(anyResourceConfig);
	}

	@Test
	void givenAnyPort_whenCreateLocalServer_thenHttpServerCanStartAndShutDown() {
		int anyPort = 8181;

		HttpServer localServer = this.httpServerFactory.createLocalServer(anyPort);

		Assertions.assertDoesNotThrow(() -> {localServer.start();localServer.shutdown();});
	}
}