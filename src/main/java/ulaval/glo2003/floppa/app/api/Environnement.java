package ulaval.glo2003.floppa.app.api;

import java.util.Arrays;

public enum Environnement {
	PRODUCTION("prod"),
	STAGING("stg");

	private final String arg;
	Environnement(String arg) {
		this.arg = arg;
	}

	public static Environnement toEnum(String arg) throws Exception {
		return Arrays.stream(Environnement.values()).filter(environnement -> environnement.arg.equalsIgnoreCase(arg)).findFirst().orElseThrow(Exception::new);
	}
}
