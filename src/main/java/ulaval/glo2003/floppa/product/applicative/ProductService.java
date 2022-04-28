package ulaval.glo2003.floppa.product.applicative;

import org.javatuples.Pair;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
	private final SellerRepository sellerRepository;
	private final ConditionSellerAssembleur conditionSellerAssembleur;
	private final ProductFactory productFactory;

	public ProductService(SellerRepository sellerRepository, ConditionSellerAssembleur conditionSellerAssembleur, ProductFactory productFactory) {
		this.sellerRepository = sellerRepository;
		this.conditionSellerAssembleur = conditionSellerAssembleur;
		this.productFactory = productFactory;
	}

	public Product createProductForSeller(String sellerId, ProductDto productDto) throws ErrorException {
		Product product = productFactory.createProduct(productDto);
		Seller seller = sellerRepository.retrieveSeller(sellerId);
		seller.getProducts().add(product);
		sellerRepository.saveSeller(seller);
		return product;
	}

	public Product retrieveOneProductWithConditions(ConditionProductDto conditionProductDto) throws ErrorException {
		ConditionSellerDto conditionSellerDto = conditionSellerAssembleur.toDto(conditionProductDto);
		return this.sellerRepository.findProducts(conditionSellerDto).stream()
				.findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
	}

	public Seller retrieveSellerByProduct(Product product) throws ErrorException {
		ConditionProductDto conditionProductDto = new ConditionProductDtoBuilder().addProductId(product.getId()).build();
		ConditionSellerDto conditionSellerDto = conditionSellerAssembleur.toDto(conditionProductDto);
		return this.sellerRepository.retrieveSeller(conditionSellerDto).stream().findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
	}

	public List<Pair<Seller, Product>> retrieveProductBySellerWithConditions(ConditionSellerDto conditionSellerDto) throws ErrorException {
		List<Pair<Seller, Product>> pairsSellerProduct = new ArrayList<>();
		List<Product> products = this.sellerRepository.findProducts(conditionSellerDto);
		for (Product product : products){
			pairsSellerProduct.add(new Pair<>(this.retrieveSellerByProduct(product), product));
		}
		return pairsSellerProduct;
	}

	public List<Product> retrieveOnlyProductBySellerWithConditions(ConditionSellerDto conditionSellerDto) throws ErrorException {
		this.sellerRepository.retrieveSeller(conditionSellerDto.getSellerId());
		return this.sellerRepository.findProducts(conditionSellerDto);
	}

	public void addViewToProduct(String productId) throws ErrorException {
		ConditionProductDto conditionProductDto = new ConditionProductDtoBuilder().addProductId(productId).build();
		ConditionSellerDto conditionSellerDto = conditionSellerAssembleur.toDto(conditionProductDto);
		Product product = sellerRepository.findProducts(conditionSellerDto).stream().findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
		product.addView();
		sellerRepository.updateProduct(product);
	}
}
