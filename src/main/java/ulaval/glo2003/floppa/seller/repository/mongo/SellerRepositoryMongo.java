package ulaval.glo2003.floppa.seller.repository.mongo;

import com.mongodb.client.MongoDatabase;
import dev.morphia.Datastore;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.List;

public class SellerRepositoryMongo implements SellerRepository {

	private final Datastore datastore;
	public SellerRepositoryMongo(Datastore datastore) {
		this.datastore = datastore;
	}

	@Override
	public void saveSeller(Seller seller) {
		datastore.save(seller);
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
