package ulaval.glo2003.floppa.seller.domain;


import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.List;
import java.util.function.Function;

public interface SellerRepository {
	void saveSeller(Seller seller);

	Seller retrieveSeller(String sellerId) throws ErrorException;

	List<Seller> retrieveSeller(List<Function<Seller, Boolean>> filterConditions);
}
