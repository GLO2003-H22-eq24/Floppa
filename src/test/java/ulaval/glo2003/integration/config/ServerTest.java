package ulaval.glo2003.integration.config;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.FloppaRunnable;

import static ulaval.glo2003.integration.config.RestAssuredConfig.PORT;

public class ServerTest {
	private HttpServer httpServer;

	@BeforeEach
	 void startServer() {
		FloppaRunnable floppaRunnable = new FloppaRunnable(PORT);
		floppaRunnable.run();
		httpServer = floppaRunnable.getHttpServer();
	}

	@AfterEach
	void stopServer() {
		httpServer.shutdown();
	}
}
