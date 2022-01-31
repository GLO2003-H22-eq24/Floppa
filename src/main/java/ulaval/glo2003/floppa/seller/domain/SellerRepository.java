package ulaval.glo2003.floppa.seller.domain;


import ulaval.glo2003.floppa.app.domain.ErrorException;

public interface SellerRepository {
	void saveSeller(Seller seller);

	Seller retrieveSeller(String sellerId) throws ErrorException;
}
