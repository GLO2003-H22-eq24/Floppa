package ulaval.glo2003.floppa.health.api;


import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.floppa.health.api.message.HealthResponse;
import ulaval.glo2003.floppa.health.applicative.HealthService;

@Path("health")
public class HealthResource {
	private final HealthService healthService;
	private final HealthAssembler healthAssembler;
	@Inject
	public HealthResource(HealthService healthService, HealthAssembler healthAssembler) {
		this.healthService = healthService;
		this.healthAssembler = healthAssembler;
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response healthCheck() {
		boolean dbCheck = healthService.checkDb();
		HealthResponse healthResponse = healthAssembler.toResponse(dbCheck);
		return Response.ok(healthResponse).build();
	}
}
