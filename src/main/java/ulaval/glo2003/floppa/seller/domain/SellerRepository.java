package ulaval.glo2003.floppa.seller.domain;


import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;

import java.util.List;

public interface SellerRepository {
	void saveSeller(Seller seller);

	Seller retrieveSeller(String sellerId) throws ErrorException;

	List<Seller> retrieveSeller(ConditionSellerDto sellerConditions);

	List<Product> findProducts(ConditionSellerDto productConditions);

	void updateProduct(Product product);

	boolean checkPersistenceState();
}
