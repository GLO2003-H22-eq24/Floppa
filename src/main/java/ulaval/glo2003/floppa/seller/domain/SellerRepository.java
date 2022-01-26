package ulaval.glo2003.floppa.seller.domain;

import jakarta.inject.Inject;
import org.glassfish.jersey.process.internal.RequestScoped;

import java.util.List;

public class SellerRepository {
    private List<Seller> sellers;

    public SellerRepository(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public void saveSeller(Seller seller) {
        sellers.add(seller);
    }
}
