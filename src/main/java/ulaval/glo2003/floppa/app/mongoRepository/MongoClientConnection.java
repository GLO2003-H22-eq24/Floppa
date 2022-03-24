package ulaval.glo2003.floppa.app.mongoRepository;

import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;


import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoClientConnection {


    private static final String CONNECTION_URL = "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";


    private MongoClient mongoClientConnection() {

        ConnectionString connectionString = new ConnectionString(CONNECTION_URL);
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

        return MongoClients.create(settings);

    }

    public MongoDatabase createDatabase(MongoEnumEnvironment.environment environment) {

        String deploymentEnvironmentMongo = "";
        MongoDatabase mongoDB = null;

        switch (environment) {
            case PRODUCTION:
                deploymentEnvironmentMongo = "floppa-production";

                break;
            case STAGING:
                deploymentEnvironmentMongo = "floppa-staging";
                break;
            case TEST:
                deploymentEnvironmentMongo = "floppa-test";
                break;

        }
        if (deploymentEnvironmentMongo != null && !deploymentEnvironmentMongo.isEmpty()) {
            mongoDB = mongoClientConnection().getDatabase(deploymentEnvironmentMongo);
        }
        return mongoDB;

    }
}
//https://docs.mongodb.com/drivers/java/sync/v4.5/fundamentals/connection/connect/#std-label-connect-to-mongodb
