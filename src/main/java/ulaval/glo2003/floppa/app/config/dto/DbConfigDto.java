package ulaval.glo2003.floppa.app.config.dto;

public class DbConfigDto {
	private final String dbName;
	private final String dbUrl;

	public DbConfigDto(String dbName, String dbUrl) {
		this.dbName = dbName;
		this.dbUrl = dbUrl;
	}

	public String getDbName() {
		return dbName;
	}

	public String getDbUrl() {
		return dbUrl;
	}
}
