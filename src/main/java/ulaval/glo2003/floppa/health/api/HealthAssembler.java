package ulaval.glo2003.floppa.health.api;

import ulaval.glo2003.floppa.health.api.message.HealthResponse;

public class HealthAssembler {
	public HealthResponse toResponse(boolean dbCheck) {
		return new HealthResponse(dbCheck, true);
	}
}
