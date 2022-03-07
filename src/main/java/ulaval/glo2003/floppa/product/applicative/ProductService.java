package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.FilterBuilderProduct;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.FilterBuilderSeller;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.applicative.ConditionBuilderSeller;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;
import java.util.List;
import java.util.function.Function;

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

	public Product retrieveProductByConditions(List<Function<Seller, Boolean>> sellerConditions, List<Function<Product, Boolean>> productConditions) throws ErrorException {
		return this.sellerRepository.findProducts(sellerConditions, productConditions).stream().findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
	}

	public Seller retrieveSellerByProduct(Product product) throws ErrorException {
    
		//List<Function<Product, Boolean>> productConditions = new FilterBuilderProduct().addProductIdCondition(product.getId()).build();
		//List<Function<Seller, Boolean>> sellerConditions = new FilterBuilderSeller().addProductFilterCondition(productConditions).build();
		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductIdCondition(product.getId()).build();
		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addProductFilterCondition(productConditions).build();

		return this.sellerRepository.retrieveSeller(sellerConditions).stream().findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
	}
}
