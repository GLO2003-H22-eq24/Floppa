package ulaval.glo2003.floppa.app.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import ulaval.glo2003.floppa.seller.api.SellerAssembler;
import ulaval.glo2003.floppa.seller.app.SellerService;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;

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
		//voir https://riptutorial.com/jersey/example/23632/basic-dependency-injection-using-jersey-s-hk2
		bindServices();
		bindAssembler();

	}

	private void bindServices() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new SellerService(new SellerRepository(new ArrayList<>()))).to(SellerService.class);
			}
		});
	}

	private void bindAssembler() {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new SellerAssembler()).to(SellerAssembler.class);
			}
		});
	}
}
