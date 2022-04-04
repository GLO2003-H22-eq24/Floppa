package ulaval.glo2003.floppa.health.applicative;

import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class HealthService {

	private final SellerRepository sellerRepository;

	public HealthService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}

	public boolean checkDb(){
		return sellerRepository.checkPersistenceState();
	}
}
