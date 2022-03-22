package ulaval.glo2003.floppa.app.mongoDB;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoClientConnection {

    private MongoClient mongoClient;
    private static final String CONNECTION_URL= "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";
    private MongoDatabase database;

    public MongoClient MongoClientConnection() {

        ConnectionString connectionString = new ConnectionString(connection);
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .applyToSocketSettings(builder -> {
                    builder.connectTimeout(1, SECONDS);
                })
                .applyToConnectionPoolSettings(builder ->
                        builder.maxConnectionIdleTime(1, SECONDS))
                .serverApi(ServerApi.builder()
                        .version(ServerApiVersion.V1)
                        .build())
                .build();
        mongoClient = MongoClients.create(settings);
        return mongoClient;

        //MongoClientURI mongoUrl = new MongoClientURI(connection);

    }

    public MongoDatabase getDatabase() {
        return mongoClient.getDatabase("Floppa");
    }
}
//https://docs.mongodb.com/drivers/java/sync/v4.5/fundamentals/connection/connect/#std-label-connect-to-mongodb