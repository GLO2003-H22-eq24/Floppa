package ulaval.glo2003.floppa.health.api.message;

public class HealthResponse {
	private boolean db;
	private boolean api;

	public HealthResponse() {
	}

	public HealthResponse(boolean dbCheck, boolean apiCheck) {
		this.db = dbCheck;
		this.api = apiCheck;
	}

	public boolean isDb() {
		return db;
	}

	public void setDb(boolean db) {
		this.db = db;
	}

	public boolean isApi() {
		return api;
	}

	public void setApi(boolean api) {
		this.api = api;
	}
}
