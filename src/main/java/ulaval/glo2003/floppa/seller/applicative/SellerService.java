package ulaval.glo2003.floppa.seller.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerFactory;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class SellerService {
	private final SellerFactory sellerFactory;
	private final SellerRepository sellerRepository;

	public SellerService(SellerFactory sellerFactory, SellerRepository sellerRepository) {
		this.sellerFactory = sellerFactory;
		this.sellerRepository = sellerRepository;
	}

	public Seller addSeller(SellerDto sellerDto) throws ErrorException {
		Seller seller = this.sellerFactory.createSeller(sellerDto);
		sellerRepository.saveSeller(seller);
		return seller;
	}

	public Seller retrieveSeller(String id) throws ErrorException {
		return this.sellerRepository.retrieveSeller(id);
	}
}
