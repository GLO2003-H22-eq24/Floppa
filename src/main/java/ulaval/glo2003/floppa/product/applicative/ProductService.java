package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class ProductService {
	private final SellerRepository sellerRepository;

	public ProductService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}


	public void createProductForSeller(String sellerId, Product product) throws ErrorException {
		Seller seller = sellerRepository.retrieveSeller(sellerId);
		seller.getProducts().add(product);
		sellerRepository.saveSeller(seller);
	}
}
