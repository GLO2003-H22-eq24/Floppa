package ulaval.glo2003.floppa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.FloppaRunnable;
import ulaval.glo2003.floppa.app.repository.mongo.Environnement;

public abstract class ServerTestIT {
	private HttpServer server;
	protected static final int PORT = 8181;

	@BeforeEach
	void startServer() {
		FloppaRunnable floppaRunnable = new FloppaRunnable(PORT, Environnement.STAGING);
		floppaRunnable.run();
		this.server = floppaRunnable.getHttpServer();
	}

	@AfterEach
	void stopServer() {
		this.server.shutdown();
	}
}
