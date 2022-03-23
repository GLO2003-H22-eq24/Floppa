package ulaval.glo2003.floppa.app.repository;

import dev.morphia.Datastore;
import org.jetbrains.annotations.NotNull;
import ulaval.glo2003.floppa.app.repository.mongo.DataStoreFactory;
import ulaval.glo2003.floppa.app.repository.mongo.Environnement;
import ulaval.glo2003.floppa.product.repository.memory.FilterInMemoryProductFactory;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;
import ulaval.glo2003.floppa.seller.repository.memory.FilterInMemorySellerFactory;
import ulaval.glo2003.floppa.seller.repository.memory.SellerRepositoryInMemory;
import ulaval.glo2003.floppa.seller.repository.mongo.SellerRepositoryMongo;

import java.net.ConnectException;
import java.util.HashMap;

public class RepositoryFactory {

	public SellerRepository createRepository(String sourcePackage, Environnement environnement) {
		SellerRepository sellerRepository;
		try {
			sellerRepository = createMongoRepository(sourcePackage, environnement);
			throw new ConnectException("DELETE ME, IM ONLY HERE TO F UP. Le temps d'avoir fait le repo");
		} catch (ConnectException e) {
			sellerRepository = new SellerRepositoryInMemory(new HashMap<>(),
					new FilterInMemorySellerFactory(new FilterInMemoryProductFactory()), new FilterInMemoryProductFactory());
		}
		return sellerRepository;
	}

	@NotNull
	private SellerRepository createMongoRepository(String sourcePackage, Environnement environnement) throws ConnectException {
		Datastore datastore = new DataStoreFactory().createDataStore(environnement);
		datastore.getMapper().mapPackage(sourcePackage);
		datastore.ensureIndexes();
		return new SellerRepositoryMongo(datastore);
	}
}
