package ulaval.glo2003.floppa.app.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.hk2.api.ServiceLocator;
import org.glassfish.hk2.utilities.ServiceLocatorUtilities;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

public class HttpServerFactory {
	private final ResourceConfig resourceConfig;
	private static final String LOCAL_HOST = "http://localhost:%s/";
	public HttpServerFactory(ResourceConfig resourceConfig) {
		this.resourceConfig = resourceConfig;
	}

	public HttpServer createLocalServer(int port){
		URI uri = URI.create(String.format(LOCAL_HOST, port));
		return GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
	}
}
