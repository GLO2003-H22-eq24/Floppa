package ulaval.glo2003.floppa.app.config;

import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.DbConfigDto;
import ulaval.glo2003.floppa.app.config.dto.HttpConfigDto;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SystemConfigResolver {
	private static final String PORT_VAR = "PORT";
	private static final String DB_NAME_VAR = "FLOPPA_DB_NAME";
	private static final String DB_URL_VAR = "FLOPPA_DB_URL";
	private static final Logger LOGGER = Logger.getLogger(SystemConfigResolver.class.getName());

	public AppConfigDto resolveEnvVariables() throws InvalidParameterException{
		HttpConfigDto httpConfigDto = createHttpConfigDto();
		DbConfigDto dbConfigDto = createDbConfigDto();
		LOGGER.log(Level.INFO, "Sys env. variables resolved - port:{0}, dbUrl:{1}, dbName:{2}",
				new String[]{String.valueOf(httpConfigDto.getPort()), dbConfigDto.getDbUrl(), dbConfigDto.getDbName()});
		return new AppConfigDto(dbConfigDto, httpConfigDto);
	}

	private DbConfigDto createDbConfigDto() {
		String dbName = fetchEnvVariable(DB_NAME_VAR);
		String dbUrl = fetchEnvVariable(DB_URL_VAR);
		return new DbConfigDto(dbName, dbUrl);
	}

	private HttpConfigDto createHttpConfigDto() {
		int port = Integer.parseInt(fetchEnvVariable(PORT_VAR));
		return new HttpConfigDto(port);

	}

	private String fetchEnvVariable(String name) {
		return Optional.ofNullable(System.getenv().get(name)).orElseThrow(() -> new InvalidParameterException("Failed to fetch sys env. variable: " + name));
	}
}
