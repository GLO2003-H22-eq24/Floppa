package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.FilterBuilderProduct;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.FilterBuilderSeller;
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

	public Product retrieveProductByConditions(List<Function<Seller, Boolean>> sellerConditions, List<Function<Product, Boolean>> productConditions) {
		return this.sellerRepository.findProductsByConditions(sellerConditions, productConditions).stream().findFirst().orElseThrow();
	}

	public Seller retrieveSellerByProduct(Product product) {
		List<Function<Product, Boolean>> productConditions = new FilterBuilderProduct().addProductIdCondition(product.getId()).build();
		List<Function<Seller, Boolean>> sellerConditions = new FilterBuilderSeller().addProductFilterCondition(productConditions).build();
		return this.sellerRepository.retrieveSeller(sellerConditions).stream().findFirst().orElseThrow();
	}
}
