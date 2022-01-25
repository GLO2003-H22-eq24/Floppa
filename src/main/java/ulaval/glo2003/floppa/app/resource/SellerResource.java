package ulaval.glo2003.floppa.app.resource;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.grizzly.http.HttpContext;
import ulaval.glo2003.floppa.app.domain.ErrorCode;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Path("/seller")
public class SellerResource {

//    @POST
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response PostSeller(@QueryParam("name") String name, @QueryParam("bio") String bio, @QueryParam("birthDate") @DateFormat Date birthDate) {
////        if (
//        System.out.println(name);
//        System.out.println(bio);
//        System.out.println(birthDate.toString());
//
//        return Response.ok().build();
//    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveSeller(HashMap<String, Object> body) {
        if (!body.containsKey("name") || !body.containsKey("bio") || !body.containsKey("birthDate")) {
            return Response.status(ErrorCode.MISSING_PARAM.getStatusCode(), ErrorCode.MISSING_PARAM.getDescription()).build();
        }

        try {
            String name = body.get("name").toString();
            String bio = body.get("bio").toString();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date birthDate = formatter.parse(body.get("birthDate").toString());

            if (name.isEmpty() || bio.isEmpty()) {
                return Response.status(ErrorCode.INVALID_PARAM.getStatusCode(), ErrorCode.INVALID_PARAM.getDescription()).build();
            }
        } catch (Exception e) {
            return Response.status(ErrorCode.INVALID_PARAM.getStatusCode(), ErrorCode.INVALID_PARAM.getDescription()).build();
        }

        return Response.ok().build();
    }
}
