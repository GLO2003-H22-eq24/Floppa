package ulaval.glo2003.floppa.health.applicative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

@ExtendWith(MockitoExtension.class)
class HealthServiceTest {
	@Mock
	private SellerRepository sellerRepository;
	@InjectMocks
	private HealthService healthService;

	@Test
	void givenPersistenceState_whenCheckDb_thenCurrentPersistenceState() {
		boolean currentState = true;
		Mockito.when(sellerRepository.checkPersistenceState()).thenReturn(currentState);

		boolean checkDb = healthService.checkDb();

		Assertions.assertTrue(checkDb);
	}
}