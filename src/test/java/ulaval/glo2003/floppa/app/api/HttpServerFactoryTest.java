package ulaval.glo2003.floppa.app.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;


@ExtendWith(MockitoExtension.class)
class HttpServerFactoryTest {
	private HttpServerFactory factory;
	private ResourceConfig resourceConfig = new ResourceConfig();

	@BeforeEach
	void givenHttpServerFactoryWithRessourceConfig() {
		this.factory = new HttpServerFactory(resourceConfig);
	}

	@Test
	void givenAnyPort_whenCreateServer_thenHttpServerCanBeStartedAndShutDown() {
		int port = 8080;

		HttpServer server = this.factory.createLocalServer(port);

		Assertions.assertDoesNotThrow(() -> {server.start(); server.shutdown();});
	}


}