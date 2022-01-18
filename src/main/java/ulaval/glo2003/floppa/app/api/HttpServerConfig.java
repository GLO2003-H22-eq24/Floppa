package ulaval.glo2003.floppa.app.api;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;

public class HttpServerConfig extends ResourceConfig {
	private static final String SRC_PACKAGE = "ulaval.glo2003.floppa";
	private static final String APP_NAME = "FLOPPA";

	public HttpServerConfig() {
		this.packages(SRC_PACKAGE);
		this.property(ServerProperties.APPLICATION_NAME, APP_NAME);
		this.registerBinders();
		this.getResources();
	}

	private void registerBinders() {
		//Ce que tu instancie ici, va pouvoir être injected dans les Ressources @Inject
	}
}
