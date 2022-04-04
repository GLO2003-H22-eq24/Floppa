package ulaval.glo2003.floppa.app.config;

import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AppConfigFactory {
	private final ArgConfigResolver argConfigResolver;
	private final SystemConfigResolver systemConfigResolver;
	private final DefaultConfigResolver defaultConfigResolver;
	private static final Logger LOGGER = Logger.getLogger(AppConfigFactory.class.getName());

	public AppConfigFactory(ArgConfigResolver argConfigResolver, SystemConfigResolver systemConfigResolver, DefaultConfigResolver defaultConfigResolver) {
		this.argConfigResolver = argConfigResolver;
		this.systemConfigResolver = systemConfigResolver;
		this.defaultConfigResolver = defaultConfigResolver;
	}

	public AppConfigDto createAppConfig(String[] args) {
		Supplier<AppConfigDto> supplierSystemConfig = systemConfigResolver::resolveEnvVariables;
		Supplier<AppConfigDto> supplierArgConfig = () -> argConfigResolver.resolveArgs(args);
		AppConfigDto computedConfigDto = Optional.ofNullable(tryCreateConfig(supplierArgConfig))
				.orElseGet(()->tryCreateConfig(supplierSystemConfig));
		return Optional.ofNullable(computedConfigDto).orElseGet(defaultConfigResolver::resolveEnvVariables);
	}

	private AppConfigDto tryCreateConfig(Supplier<AppConfigDto> supplier){
		try {
			return supplier.get();
		}catch (InvalidParameterException invalidParameterException){
			LOGGER.log(Level.WARNING, invalidParameterException.getMessage());
			return null;
		}
	}
}
