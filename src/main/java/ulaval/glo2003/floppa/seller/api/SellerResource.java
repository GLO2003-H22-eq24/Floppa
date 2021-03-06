package ulaval.glo2003.floppa.seller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.api.HttpParamUtil;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.api.request.SellerRequest;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;
import ulaval.glo2003.floppa.seller.applicative.SellerDto;
import ulaval.glo2003.floppa.seller.applicative.SellerService;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;

@Path("/sellers")
public class SellerResource {
    private final SellerService sellerService;
    private final SellerAssembler sellerAssembler;

    @Inject
    public SellerResource(SellerService sellerService, SellerAssembler sellerAssembler) {
        this.sellerService = sellerService;
        this.sellerAssembler = sellerAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveSeller(SellerRequest sellerRequest, @Context UriInfo uriInfo) throws ErrorException {
        SellerDto sellerDto = sellerAssembler.toDto(sellerRequest);
        Seller seller = sellerService.addSeller(sellerDto);
        return Response.status(Response.Status.CREATED).location(URI.create(uriInfo.getBaseUri() + "sellers/" + seller.getId())).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSeller(@PathParam("id") String id, @Context HttpHeaders httpheaders) throws ErrorException {
        if (id.equals("@me") || id.equals("%40me")){
            return retrieveMeSeller(httpheaders);
        }else {
            Seller seller = sellerService.retrieveSeller(HttpParamUtil.fetchId(id));
            SellerResponse sellerResponse = sellerAssembler.toResponse(seller, false);
            return Response.ok().entity(sellerResponse).build();
        }
    }

    private Response retrieveMeSeller(HttpHeaders httpheaders) throws ErrorException {
        String idSeller = HttpParamUtil.retrieveSellerIdFromHeaders(httpheaders);
        Seller seller = sellerService.retrieveSeller(idSeller);
        SellerResponse sellerResponse = sellerAssembler.toResponse(seller, true);
        return Response.ok().entity(sellerResponse).build();
    }
}
