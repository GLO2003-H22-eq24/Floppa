package ulaval.glo2003.floppa.seller.app;

import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class SellerService {
    private SellerRepository sellerRepository;

    public SellerService(SellerRepository sellerRepository) {
        this.sellerRepository = sellerRepository;
    }

    public void SaveSeller(Seller seller) {
        sellerRepository.saveSeller(seller);
    }
}
