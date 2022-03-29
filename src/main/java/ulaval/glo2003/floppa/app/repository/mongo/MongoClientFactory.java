package ulaval.glo2003.floppa.app.repository.mongo;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.net.ConnectException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.util.concurrent.TimeUnit.SECONDS;

public class MongoClientFactory {
	private static final Logger LOGGER = Logger.getLogger(MongoClientFactory.class.getName());

	public com.mongodb.client.MongoClient createMongoClient(String dbUrl) throws ConnectException {
		try {
			ConnectionString connectionString = new ConnectionString(dbUrl);
			MongoClientSettings settings = createMongoClientSettings(connectionString);
			MongoClient mongoClient = MongoClients.create(settings);
			testConnection(mongoClient);
			return mongoClient;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Failed to connect to  DB using URL: {0}", dbUrl);
			throw new ConnectException("Failed to connect to DB");
		}
	}

	private void testConnection(MongoClient mongoClient) {
		mongoClient.listDatabaseNames().first();
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
