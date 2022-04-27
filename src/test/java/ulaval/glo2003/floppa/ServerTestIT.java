package ulaval.glo2003.floppa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import ulaval.glo2003.FloppaRunnable;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.DbConfigDto;
import ulaval.glo2003.floppa.app.config.dto.HttpConfigDto;

public abstract class ServerTestIT {
	private HttpServer server;
	protected static final int PORT = 8181;
	protected static final String dbUrl = "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";
	protected static final String dbName = "floppa-staging";

	@BeforeEach
	void startServer() {
		//uncomment to use db repository
		//FloppaRunnable floppaRunnable = new FloppaRunnable(new AppConfigDto(new DbConfigDto(dbName, dbUrl), new HttpConfigDto(PORT)));
		FloppaRunnable floppaRunnable = new FloppaRunnable(new AppConfigDto(new HttpConfigDto(PORT)));
		floppaRunnable.run();
		this.server = floppaRunnable.getHttpServer();
	}

	@AfterEach
	void stopServer() {
		this.server.shutdown();
	}
}
