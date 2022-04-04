package ulaval.glo2003.floppa.app.config;

import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.HttpConfigDto;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DefaultConfigResolver {

	private static final int DEFAULT_PORT = 8080;
	private static final Logger LOGGER = Logger.getLogger(DefaultConfigResolver.class.getName());

	public AppConfigDto resolveEnvVariables() throws InvalidParameterException {
		HttpConfigDto httpConfigDto = new HttpConfigDto(DEFAULT_PORT);
		LOGGER.log(Level.INFO, "Default config resolved - port:{0}",
				new String[]{String.valueOf(httpConfigDto.getPort())});
		return new AppConfigDto(httpConfigDto);
	}
}
