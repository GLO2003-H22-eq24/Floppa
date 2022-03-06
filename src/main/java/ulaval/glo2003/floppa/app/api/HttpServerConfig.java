package ulaval.glo2003.floppa.app.api;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import ulaval.glo2003.floppa.app.api.mapper.ErrorExceptionAssembler;
import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.product.api.ProductAssembler;
import ulaval.glo2003.floppa.product.applicative.ProductService;
import ulaval.glo2003.floppa.seller.api.SellerAssembler;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;
import ulaval.glo2003.floppa.seller.repository.SellerRepositoryInMemory;

import java.util.HashMap;

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
		SellerRepository sellerRepository = new SellerRepositoryInMemory(new HashMap<>());
		bindRepository(sellerRepository);
		bindService(sellerRepository);
		bindAssembler();
	}

	private void bindRepository(SellerRepository sellerRepository) {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(sellerRepository).to(SellerRepository.class);
			}
		});
	}

	private void bindService(SellerRepository sellerRepository) {
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new ProductService(sellerRepository)).to(ProductService.class);
			}
		});
	}

	private void bindAssembler() {
		OffersAssembler offersAssembler = new OffersAssembler();
		ProductAssembler productAssembler = new ProductAssembler(offersAssembler);
		SellerAssembler sellerAssembler = new SellerAssembler(productAssembler);
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new SellerAssembler(new ProductAssembler(new OffersAssembler()))).to(SellerAssembler.class);
			}
		});
		register(new AbstractBinder() {
			@Override
			protected void configure() {
				bind(new ErrorExceptionAssembler()).to(ErrorExceptionAssembler.class);
			}
		});
	}
}
