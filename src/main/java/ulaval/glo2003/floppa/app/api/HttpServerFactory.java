package ulaval.glo2003.floppa.app.api;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import ulaval.glo2003.floppa.app.config.ArgConfigResolver;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HttpServerFactory {
	private final ResourceConfig resourceConfig;
	private static final String LOCAL_HOST = "http://localhost:%s/";
	private static final Logger LOGGER = Logger.getLogger(HttpServerFactory.class.getName());
	public HttpServerFactory(ResourceConfig resourceConfig) {
		this.resourceConfig = resourceConfig;
	}

	public HttpServer createLocalServer(int port){
		//String uri = System.getenv().get("FLOPPA_URI");
		//if (uri == null){
		//	uri = String.format(LOCAL_HOST, port);
		//	LOGGER.log(Level.INFO, "using localhost");
		//}
		URI uri;
		try {
			uri = new URI(null,null,null, null, null);
		} catch (URISyntaxException e) {
			uri = URI.create(String.format(LOCAL_HOST, port));
		}
		return GrizzlyHttpServerFactory.createHttpServer(uri, resourceConfig);
	}
}
