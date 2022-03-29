package ulaval.glo2003.floppa.app.repository.mongo;

import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(DataStoreFactory.class.getName());

    public Datastore createDataStore(String dbName, String sourcePackage, com.mongodb.client.MongoClient mongoClient) {
            Datastore datastore = Morphia.createDatastore(mongoClient, dbName);
            datastore.getMapper().mapPackage(sourcePackage);
            datastore.ensureIndexes();
            LOGGER.log(Level.INFO, "Connected to  DB named: {0}", datastore.getDatabase().getName());
            return datastore;
    }
}