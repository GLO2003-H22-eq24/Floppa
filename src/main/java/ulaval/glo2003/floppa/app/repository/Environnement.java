package ulaval.glo2003.floppa.app.repository;

import java.util.Arrays;

public enum Environnement {
	//TODO DEMANDER AU PROF SI C'EST CORRECT D'AVOIR UNE ENUM ICI avec les config
	//dépendance avec la couche API + magic string dans le pom
	PRODUCTION("prod", "floppa-production"),
	STAGING("stg", "floppa-staging"),
	LOCAL("local", "floppa-dev"),
	IN_MEMORY("mem", null);

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
