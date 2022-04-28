package ulaval.glo2003.floppa.app.api;

import com.google.common.base.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class HttpParamUtilTest {

	@Test
	void givenAnyId_whenFetchId_thenReturnId() throws ErrorException {
		String anyId = UUID.randomUUID().toString();

		String id = HttpParamUtil.fetchId(anyId);

		Assertions.assertEquals(anyId, id);
	}

	@Test
	void givenNoId_whenFetchId_thenReturnId() {
		String anyId = null;

		Executable idSupplier = () -> HttpParamUtil.fetchId(anyId);

		Assertions.assertThrows(ErrorException.class, idSupplier);
	}
}