package ulaval.glo2003.floppa.seller.domain;

import java.util.List;

public class SellerRepository {
    private List<Seller> sellers;

    public SellerRepository(List<Seller> sellers) {
        this.sellers = sellers;
    }

    public void saveSeller(Seller seller) {
        sellers.add(seller);
    }

    public int getSellerIndex(Seller seller) {
        return sellers.indexOf(seller);
    }
}
