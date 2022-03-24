package ulaval.glo2003.floppa.app.repository.mongo;

import java.util.Arrays;

public enum Environnement {
	PRODUCTION("prod", "floppa-production"),
	STAGING("stg", "floppa-staging");

	private final String arg;
	private final String databaseName;
	Environnement(String arg, String databaseName) {
		this.arg = arg;
		this.databaseName = databaseName;
	}

	public static Environnement toEnum(String arg) throws Exception {
		return Arrays.stream(Environnement.values()).filter(environnement -> environnement.arg.equalsIgnoreCase(arg)).findFirst().orElseThrow(Exception::new);
	}

	public String getDatabaseName() {
		return databaseName;
	}
}
