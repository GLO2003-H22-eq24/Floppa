package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class ProductService {
	private final SellerRepository sellerRepository;

	public ProductService(SellerRepository sellerRepository) {
		this.sellerRepository = sellerRepository;
	}


	public void createProductForSeller(String sellerId, Product product) {
		//TODO: ajouter le product au seller
	}
}
