package ulaval.glo2003.floppa.seller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.api.message.SellerRequest;
import ulaval.glo2003.floppa.seller.api.message.SellerResponse;
import ulaval.glo2003.floppa.seller.applicative.SellerDto;
import ulaval.glo2003.floppa.seller.applicative.SellerService;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;

@Path("/sellers")
public class SellerResource {
    private final SellerService sellerService;
    private final SellerAssembler sellerAssembler;
    private static final String SELLER_ID_HEADER = "X-Seller-Id";

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
    public Response retrieveSeller(@PathParam("id") String id) throws ErrorException {
        Seller seller = sellerService.retrieveSeller(id);
        SellerResponse sellerResponse = sellerAssembler.toResponse(seller);
        return Response.ok().entity(sellerResponse).build();
    }

    @GET
    @Path("/%40me")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveMeSeller(@Context HttpHeaders httpheaders) throws ErrorException {
        String idSeller = httpheaders.getHeaderString(SELLER_ID_HEADER);
        Seller seller = sellerService.retrieveSeller(idSeller);
        SellerResponse sellerResponse = sellerAssembler.toResponse(seller);
        return Response.ok().entity(sellerResponse).build();
    }
}
