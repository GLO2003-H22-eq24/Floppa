package ulaval.glo2003.floppa.app.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DefaultConfigResolverTest {
	private final DefaultConfigResolver defaultConfigResolver = new DefaultConfigResolver();
	private static final int DEFAULT_PORT = 8080;
	@Test
	void givenDefaultConfigResolver_whenResolveEnvVariables_thenAppConfigDtoWithDefaultValues() {

		AppConfigDto appConfigDto = defaultConfigResolver.resolveEnvVariables();

		Assertions.assertEquals(DEFAULT_PORT, appConfigDto.getHttpConfigDto().getPort());
	}
}