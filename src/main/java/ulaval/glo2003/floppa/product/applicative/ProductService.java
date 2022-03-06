package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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

	public List<Product> retriveProductByConditions(List<Function<Seller, Boolean>> sellerConditions, List<Function<Product, Boolean>> productConditions) {
		List<Product> products = sellerRepository.retrieveSeller(sellerConditions).stream()
				.map(Seller::getProducts)
				.flatMap(List::stream).collect(Collectors.toList());
		return products.stream()
				.filter(product -> productConditions.stream()
						.allMatch(productCondition -> productCondition.apply(product)))
				.collect(Collectors.toList());
	}

	public Seller retriveSellerByProduct(Product product) {
		//TODO
		return null;
	}
}
