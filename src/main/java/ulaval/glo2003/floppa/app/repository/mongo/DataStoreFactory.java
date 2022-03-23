package ulaval.glo2003.floppa.app.repository.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DataStoreFactory {
    private static final Logger LOGGER = Logger.getLogger(DataStoreFactory.class.getName());
    private static final String CONNECTION_URL= "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";

    public Datastore createDataStore(Environnement environnement) throws ConnectException {
        try {
            Datastore datastore = Morphia.createDatastore(createMongoClient(), environnement.getDatabaseName());
            LOGGER.log(Level.INFO, "Connected to  DB: {0}", environnement.getDatabaseName());
            return datastore;
        }catch (Exception e){
            throw new ConnectException("Failed to connect to DB");
        }
    }

    private com.mongodb.client.MongoClient createMongoClient() {
        ConnectionString connectionString = new ConnectionString(CONNECTION_URL);
        MongoClientSettings settings = createMongoClientSettings(connectionString);
        return MongoClients.create(settings);
    }

    private MongoClientSettings createMongoClientSettings(ConnectionString connectionString) {
        return MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSocketSettings(builder -> builder.connectTimeout(1, SECONDS))
                .applyToConnectionPoolSettings(builder ->
                        builder.maxConnectionIdleTime(1, SECONDS))
                .build();
    }
}