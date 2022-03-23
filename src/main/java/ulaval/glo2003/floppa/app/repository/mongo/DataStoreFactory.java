package ulaval.glo2003.floppa.app.repository.mongo;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import dev.morphia.Datastore;
import dev.morphia.Morphia;

import static java.util.concurrent.TimeUnit.SECONDS;

public class DataStoreFactory {
    private static final String CONNECTION_URL= "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";

    public Datastore createDataStore(Environnement environnement) {
        return Morphia.createDatastore(createMongoClient(), environnement.getDatabaseName());
    }

    private MongoClient createMongoClient() {
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
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
    }
}