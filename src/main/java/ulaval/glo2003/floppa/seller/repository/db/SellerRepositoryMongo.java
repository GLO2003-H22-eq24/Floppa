package ulaval.glo2003.floppa.seller.repository.db;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.List;

public class SellerRepositoryMongo implements SellerRepository {
	@Override
	public void saveSeller(Seller seller) {
//TODO
	}

	@Override
	public Seller retrieveSeller(String sellerId) throws ErrorException {
		return null; //TODO
	}

	@Override
	public List<Seller> retrieveSeller(ConditionSellerDto sellerConditions) {
		return null;//TODO
	}

	@Override
	public List<Product> findProducts(ConditionSellerDto productConditions) {
		return null;//TODO
	}

	@Override
	public void updateProduct(Product product) {
		//TODO
	}
}
