package ulaval.glo2003.floppa.app.repository.mongo;

import dev.morphia.Datastore;
import dev.morphia.Morphia;
import ulaval.glo2003.floppa.app.repository.Environnement;

import java.util.logging.Level;
import java.util.logging.Logger;

public class DataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(DataStoreFactory.class.getName());

    public Datastore createDataStore(Environnement environnement, String sourcePackage, com.mongodb.client.MongoClient mongoClient) {
            Datastore datastore = Morphia.createDatastore(mongoClient, environnement.getDatabaseName());
            datastore.getMapper().mapPackage(sourcePackage);
            datastore.ensureIndexes();
            LOGGER.log(Level.INFO, "Connected to  DB: {0}", datastore.getDatabase().getName());
            return datastore;
    }
}