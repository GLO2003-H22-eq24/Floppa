package ulaval.glo2003.floppa.app.mongoDB;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoClientConnection {
    public static void main(String[] args) {
        String user = "floppa-api";
        String pwd = "XxIDt04RxHTps0YZ";

        String connection = "mongodb+srv://" + user + ":" + pwd + "@floppa.3oieg.mongodb.net/myFirstDatabase?retryWrites=true&w=majority";

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
        MongoClient mongoClient = MongoClients.create(settings);
        MongoClientURI mongoUrl  = new MongoClientURI(connection);
        MongoDatabase database = mongoClient.getDatabase("Floppa");


    }
}
//https://docs.mongodb.com/drivers/java/sync/v4.5/fundamentals/connection/connect/#std-label-connect-to-mongodb