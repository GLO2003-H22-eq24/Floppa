package ulaval.glo2003.floppa.seller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.app.SellerService;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.net.URI;

@Path("/seller")
public class SellerResource {
    private final SellerService sellerService;
    private final SellerAssembler sellerAssembler;

    @Inject
    public SellerResource(SellerService sellerService, SellerAssembler sellerAssembler) {
       this.sellerService = sellerService;
       this.sellerAssembler=sellerAssembler;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveSeller(SellerDto sellerDto, @Context UriInfo uriInfo) throws ErrorException {
        Seller seller = sellerAssembler.fromDto(sellerDto);
        sellerService.SaveSeller(seller);
        return Response.ok().location(URI.create(uriInfo.getBaseUri() + "sellers/" + sellerService.getSellerIndex(seller))).build();
    }
}
