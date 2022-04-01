package ulaval.glo2003.floppa.app.config;

import ulaval.glo2003.floppa.app.config.dto.AppConfigDto;
import ulaval.glo2003.floppa.app.config.dto.DbConfigDto;
import ulaval.glo2003.floppa.app.config.dto.HttpConfigDto;

import java.security.InvalidParameterException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ArgConfigResolver {
	private static final int PORT_INDEX = 0;
	private static final int DB_NAME_INDEX = 1;
	private static final int DB_URL_INDEX = 2;
	private static final Logger LOGGER = Logger.getLogger(ArgConfigResolver.class.getName());

	public AppConfigDto resolveArgs(String[] args) throws InvalidParameterException{
		HttpConfigDto httpConfigDto = createHttpConfigDto(args);
		DbConfigDto dbConfigDto = createDbConfigDto(args);
		LOGGER.log(Level.INFO, "Args resolved - port:{0}, dbUrl:{1}, dbName:{2}",
				new String[]{String.valueOf(httpConfigDto.getPort()), dbConfigDto.getDbUrl(), dbConfigDto.getDbName()});
		return new AppConfigDto(dbConfigDto, httpConfigDto);
	}

	private DbConfigDto createDbConfigDto(String[] args) {
		String dbName = retrieveDbNameArg(args);
		String dbUrl = retrieveDbUrlArg(args);
		return new DbConfigDto(dbName, dbUrl);
	}

	private HttpConfigDto createHttpConfigDto(String[] args) {
		int port = retrievePortArg(args);
		return new HttpConfigDto(port);
	}

	private int retrievePortArg(String[] args) {
		try {
			return Integer.parseInt(args[PORT_INDEX]);
		} catch (Exception e) {
			throw new InvalidParameterException("Missing valid positional argument in -Dport=");
		}
	}

	private String retrieveDbNameArg(String[] args) {
		try {
			String dbName = args[DB_NAME_INDEX];
			if (dbName.isBlank()) {
				throw new InvalidParameterException();
			}
			return dbName;
		} catch (Exception e) {
			throw new InvalidParameterException("Missing valid positional argument in -DbdName=");
		}
	}


	private String retrieveDbUrlArg(String[] args) {
		try {
			String dbUrl = args[DB_URL_INDEX];
			if (dbUrl.isBlank()) {
				throw new InvalidParameterException();
			}
			return dbUrl;
		} catch (Exception e) {
			throw new InvalidParameterException("Missing valid positional argument in -DbdUrl=");
		}
	}

}
