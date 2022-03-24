package ulaval.glo2003.floppa.app.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import ulaval.glo2003.floppa.app.repository.Environnement;

import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoClientFactory {
	private static final Logger LOGGER = Logger.getLogger(MongoClientFactory.class.getName());
	private static final String ATLAS_CONNECTION_URL = "mongodb+srv://floppa-api:XxIDt04RxHTps0YZ@floppa.3oieg.mongodb.net/Floppa?retryWrites=true&w=majority";
	private static final String LOCAL_CONNECTION_URL = "mongodb://localhost";

	public com.mongodb.client.MongoClient createMongoClient(Environnement environnement) throws ConnectException {
		try {
			ConnectionString connectionString = environnement == Environnement.LOCAL ? new ConnectionString(LOCAL_CONNECTION_URL) :
					new ConnectionString(ATLAS_CONNECTION_URL);
			MongoClientSettings settings = createMongoClientSettings(connectionString);
			MongoClient mongoClient = MongoClients.create(settings);
			mongoClient.listDatabaseNames().first();
			return mongoClient;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to connect to  DB: {0}", environnement.getDatabaseName());
			throw new ConnectException("Failed to connect to DB");
		}
	}

	private MongoClientSettings createMongoClientSettings(ConnectionString connectionString) {
		return MongoClientSettings.builder()
				.applyConnectionString(connectionString)
				.applyToSocketSettings(builder -> builder.connectTimeout(2, SECONDS))
				.applyToConnectionPoolSettings(builder ->
						builder.maxConnectionIdleTime(2, SECONDS))
				.applyToClusterSettings(builder -> builder.serverSelectionTimeout(2, SECONDS))
				.build();
	}
}
