package ulaval.glo2003.floppa.app.api;

import org.glassfish.grizzly.http.server.HttpServer;
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
		String uri = System.getenv().get("FLOPPA_URI");
		if (uri == null){
			uri = String.format(LOCAL_HOST, port);
		}
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(uri), resourceConfig);
	}
}
