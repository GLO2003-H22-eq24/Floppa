package ulaval.glo2003.floppa.app.repository;

import com.mongodb.client.MongoClient;
import dev.morphia.Datastore;
import org.jetbrains.annotations.NotNull;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.DbConfigDto;
import ulaval.glo2003.floppa.app.repository.mongo.DataStoreFactory;
import ulaval.glo2003.floppa.app.repository.mongo.MongoClientFactory;
import ulaval.glo2003.floppa.product.repository.memory.FilterInMemoryProductFactory;
import ulaval.glo2003.floppa.product.repository.mongo.MorphiaFilterProductFactory;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;
import ulaval.glo2003.floppa.seller.repository.memory.FilterInMemorySellerFactory;
import ulaval.glo2003.floppa.seller.repository.memory.SellerRepositoryInMemory;
import ulaval.glo2003.floppa.seller.repository.mongo.MorphiaFilterSellerFactory;
import ulaval.glo2003.floppa.seller.repository.mongo.SellerRepositoryMongo;

import java.net.ConnectException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RepositoryFactory {
	private static final Logger LOGGER = Logger.getLogger(RepositoryFactory.class.getName());
	private final MongoClientFactory mongoClientFactory;
	private final DataStoreFactory dataStoreFactory;

	public RepositoryFactory(MongoClientFactory mongoClientFactory, DataStoreFactory dataStoreFactory) {
		this.mongoClientFactory = mongoClientFactory;
		this.dataStoreFactory = dataStoreFactory;
	}

	public SellerRepository createRepository(String sourcePackage, AppConfigDto appConfigDto) {
		try {
			return createMongoRepository(sourcePackage, appConfigDto.getDbConfigDto());
		} catch (ConnectException e) {
			LOGGER.log(Level.SEVERE, "Using in memory repository");
			return createInMemoryRepository();
		}
	}

	@NotNull
	private SellerRepositoryInMemory createInMemoryRepository() {
		return new SellerRepositoryInMemory(new HashMap<>(),
				new FilterInMemorySellerFactory(new FilterInMemoryProductFactory()), new FilterInMemoryProductFactory());
	}

	@NotNull
	private SellerRepository createMongoRepository(String sourcePackage, DbConfigDto dbConfigDto) throws ConnectException {
		MongoClient mongoClient = mongoClientFactory.createMongoClient(dbConfigDto.getDbUrl());
		Datastore datastore = dataStoreFactory.createDataStore(dbConfigDto.getDbName(), sourcePackage, mongoClient);
		return new SellerRepositoryMongo(datastore, new MorphiaFilterSellerFactory(new MorphiaFilterProductFactory()));
	}
}
