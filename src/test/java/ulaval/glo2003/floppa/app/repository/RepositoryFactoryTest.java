package ulaval.glo2003.floppa.app.repository;

import com.mongodb.client.internal.MongoClientImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.DbConfigDto;
import ulaval.glo2003.floppa.app.repository.mongo.DataStoreFactory;
import ulaval.glo2003.floppa.app.repository.mongo.MongoClientFactory;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;
import ulaval.glo2003.floppa.seller.repository.memory.SellerRepositoryInMemory;

import java.net.ConnectException;


@ExtendWith(MockitoExtension.class)
class RepositoryFactoryTest {
	@Mock
	private MongoClientFactory mongoClientFactory;
	@InjectMocks
	private RepositoryFactory repositoryFactory;
	private final String anySrcPackage = "src package";
	@Mock
	private AppConfigDto anyAppConfigDto;
	@Mock
	private DbConfigDto dbConfigDto;

	@Test
	void givenInvalidMongoClient_whenCreateRepository_thenSellerRepositoryInMemory() throws ConnectException {
		Mockito.when(anyAppConfigDto.getDbConfigDto()).thenReturn(dbConfigDto);
		Mockito.when(mongoClientFactory.createMongoClient(Mockito.any())).thenThrow(new ConnectException());

		SellerRepository repository = repositoryFactory.createRepository(anySrcPackage, anyAppConfigDto);

		Assertions.assertTrue(repository instanceof SellerRepositoryInMemory);
	}

}