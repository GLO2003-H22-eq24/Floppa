package ulaval.glo2003.floppa.seller.domain;


import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;

import java.util.List;
import java.util.function.Function;

public interface SellerRepository {
	void saveSeller(Seller seller);

	Seller retrieveSeller(String sellerId) throws ErrorException;

	List<Product> findProductsByConditions(List<Function<Seller, Boolean>> sellerConditions, List<Function<Product, Boolean>> productConditions);

	List<Seller> retrieveSeller(List<Function<Seller, Boolean>> sellerConditions);
}
