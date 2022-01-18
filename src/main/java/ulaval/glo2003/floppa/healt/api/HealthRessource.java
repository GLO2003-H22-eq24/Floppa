package ulaval.glo2003.floppa.healt.api;


import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


public class HealthRessource {

	@GET
	@Path("health")
	@Produces(MediaType.APPLICATION_JSON)
	public Response healthCheck() {
		return Response.ok().build();
	}
}
