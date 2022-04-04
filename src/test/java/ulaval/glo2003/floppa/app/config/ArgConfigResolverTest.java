package ulaval.glo2003.floppa.app.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;

import java.net.ConnectException;
import java.security.InvalidParameterException;
import java.util.function.Supplier;

@ExtendWith(MockitoExtension.class)
class ArgConfigResolverTest {

	private final ArgConfigResolver argConfigResolver = new ArgConfigResolver();

	@Test
	void givenValidArgs_whenResolveArgs_thenAppConfigDtoWithArgs() throws ConnectException {
		String anyPort = "8080";
		String anyDbName = "anyDbName";
		String anyUrl = "anyUrl";
		String[] validArgs = new String[]{anyPort, anyDbName, anyUrl};

		AppConfigDto appConfigDto = argConfigResolver.resolveArgs(validArgs);

		Assertions.assertEquals(anyPort, String.valueOf(appConfigDto.getHttpConfigDto().getPort()));
		Assertions.assertEquals(anyDbName, appConfigDto.getDbConfigDto().getDbName());
		Assertions.assertEquals(anyUrl, appConfigDto.getDbConfigDto().getDbUrl());
	}

	@Test
	void givenAnyMissingArg_whenResolveArgs_thenInvalidParameterException() {
		String anyPort = "8080";
		String anyDbName = "anyDbName";
		String[] validArgs = new String[]{anyPort, anyDbName};

		Supplier<AppConfigDto> appConfigDtoSupplier = () -> argConfigResolver.resolveArgs(validArgs);

		Assertions.assertThrows(InvalidParameterException.class, appConfigDtoSupplier::get);
	}
}