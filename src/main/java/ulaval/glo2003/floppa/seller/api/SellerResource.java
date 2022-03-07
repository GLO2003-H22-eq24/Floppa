package ulaval.glo2003.floppa.seller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoRequest;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.net.URI;

@Path("/sellers")
public class SellerResource {
    private final SellerRepository sellerRepository;
    private final SellerAssembler sellerAssembler;

    @Inject
    public SellerResource(SellerRepository sellerRepository, SellerAssembler sellerAssembler) {
        this.sellerRepository = sellerRepository;
        this.sellerAssembler=sellerAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveSeller(SellerDtoRequest sellerDtoRequest, @Context UriInfo uriInfo) throws ErrorException {
        Seller seller = sellerAssembler.fromDto(sellerDtoRequest);
        sellerRepository.saveSeller(seller);
        return Response.status(Response.Status.CREATED).location(URI.create(uriInfo.getBaseUri() + "sellers/" + seller.getId())).build();
    }

    @GET
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSeller(@PathParam("id") String id) throws ErrorException {
        Seller seller = sellerRepository.retrieveSeller(id);
        SellerDtoResponse sellerDtoResponse = sellerAssembler.toDto(seller);
        return Response.ok().entity(sellerDtoResponse).build();
    }
}
