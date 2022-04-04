package ulaval.glo2003.floppa.app.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AppConfigFactoryTest {
	private final String val = "asdbg";
	@Mock
	private ArgConfigResolver argConfigResolver;
	@Mock
	private SystemConfigResolver systemConfigResolver;
	@Mock
	private DefaultConfigResolver defaultConfigResolver;
	@InjectMocks
	private AppConfigFactory appConfigFactory;
	private final String[] args = new String[]{"arg"};
	@Mock
	private AppConfigDto validAppConfigDto;


	@Test
	void givenValidSystemConfig_whenCreateAppConfig_thenUseSystemConfigResolver() {
		Mockito.lenient().when(systemConfigResolver.resolveEnvVariables()).thenReturn(validAppConfigDto);
		Mockito.lenient().when(argConfigResolver.resolveArgs(Mockito.any())).thenReturn(null);
		Mockito.lenient().when(defaultConfigResolver.resolveEnvVariables()).thenReturn(null);

		AppConfigDto appConfig = appConfigFactory.createAppConfig(args);

		Assertions.assertEquals(validAppConfigDto, appConfig);
	}

	@Test
	void givenValidArgConfigResolver_whenCreateAppConfig_thenUseArgConfigResolver() {
		Mockito.lenient().when(systemConfigResolver.resolveEnvVariables()).thenReturn(null);
		Mockito.lenient().when(argConfigResolver.resolveArgs(Mockito.any())).thenReturn(validAppConfigDto);
		Mockito.lenient().when(defaultConfigResolver.resolveEnvVariables()).thenReturn(null);

		AppConfigDto appConfig = appConfigFactory.createAppConfig(args);

		Assertions.assertEquals(validAppConfigDto, appConfig);
	}


	@Test
	void givenValidDefaultConfigResolver_whenCreateAppConfig_thenUseDefaultConfigResolver() {
		Mockito.lenient().when(systemConfigResolver.resolveEnvVariables()).thenReturn(null);
		Mockito.lenient().when(argConfigResolver.resolveArgs(Mockito.any())).thenReturn(null);
		Mockito.lenient().when(defaultConfigResolver.resolveEnvVariables()).thenReturn(validAppConfigDto);

		AppConfigDto appConfig = appConfigFactory.createAppConfig(args);

		Assertions.assertEquals(validAppConfigDto, appConfig);
	}
}