package ulaval.glo2003.floppa.app.resource;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.floppa.app.annotation.dateformat.DateFormat;

import java.util.Date;

@Path("/seller")
public class SellerResource {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response PostSeller(@QueryParam("name") String name, @QueryParam("bio") String bio, @QueryParam("birthDate") @DateFormat Date birthDate) {
        System.out.println(name);
        System.out.println(bio);
        System.out.println(birthDate.toString());

        return Response.ok().build();
    }
}
