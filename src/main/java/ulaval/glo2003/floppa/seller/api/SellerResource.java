package ulaval.glo2003.floppa.seller.api;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.app.SellerService;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;

@Path("/seller")
public class SellerResource {
    private final SellerService sellerService;
    private final SellerAssembler sellerAssembler;

    //J'ai aucune idée comment faire fonctionner le Inject, alors j'ai mis ça pour l'instant
    //Ce qui est en commentaire est ce qui devrait être là théoriquement
    @Inject
    public SellerResource(SellerService sellerService, SellerAssembler sellerAssembler) {
       this.sellerService = sellerService;
       //ajout sellerAssembler ici ? Je crois que c'est good demême -cw
       this.sellerAssembler=sellerAssembler;

        //this.sellerService = new SellerService(new SellerRepository(new ArrayList<>()));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SaveSeller(SellerDto sellerDto) throws ErrorException {
        Seller seller = sellerAssembler.fromDto(sellerDto);
        sellerService.SaveSeller(seller);
        return Response.ok().build();
    }
}
