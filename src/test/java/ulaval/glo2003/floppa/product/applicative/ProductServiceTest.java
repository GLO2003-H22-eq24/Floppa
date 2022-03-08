package ulaval.glo2003.floppa.product.applicative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@Mock
	private SellerRepository sellerRepository;
	@InjectMocks
	private ProductService productService;
	@Mock
	private Seller seller;
	@Mock
	private Product product;
	private final String anyId = "anyId";

	@BeforeEach
	void repositoryWithSellerAndProducts() throws ErrorException {
		Mockito.lenient().when(sellerRepository.retrieveSeller(Mockito.anyString())).thenReturn(seller);
		List<Product> products = new ArrayList<>();
		products.add(product);
		Mockito.lenient().when(sellerRepository.findProducts(Mockito.any(), Mockito.any())).thenReturn(products);
	}

	@Test
	void givenSeller_whenCreateProductForSeller_ThenRetrievedSellerIsSaved() throws ErrorException {


		productService.createProductForSeller(anyId, product);

		Mockito.verify(sellerRepository, Mockito.times(1)).saveSeller(seller);
	}

	@Test
	void givenSellerId_whenCreateProductForSeller_ThenRetrievedSellerById() throws ErrorException {
		String sellerId = "id";

		productService.createProductForSeller(sellerId, product);

		Mockito.verify(sellerRepository, Mockito.times(1)).retrieveSeller(sellerId);
	}

	@Test
	void givenSellerWithProductsAndNewProduct_whenCreateProductForSeller_ThenNewProductIsAdded() throws ErrorException {
		List<Product> productsList = new ArrayList<>();
		Mockito.when(seller.getProducts()).thenReturn(productsList);

		productService.createProductForSeller(anyId, product);

		Assertions.assertTrue(productsList.contains(product));
	}

	@Test
	void givenSellerConditionsAndProductConditionsWithProducts_whenRetrieveProductByConditions_ThenFindProducts() throws ErrorException {
		List<Function<Seller, Boolean>> sellerConditions = new ArrayList<>();
		List<Function<Product, Boolean>> productConditions = new ArrayList<>();

		productService.retrieveProductByConditions(sellerConditions, productConditions);

		Mockito.verify(sellerRepository, Mockito.times(1)).findProducts(sellerConditions, productConditions);
	}

	@Test
	void givenSellerProductWithNoProducts_whenRetrieveSellerByProduct_ThenITEM_NOT_FOUND() throws ErrorException {
		Mockito.lenient().when(sellerRepository.retrieveSeller(Mockito.anyList())).thenReturn(new ArrayList<>());

		Assertions.assertThrows(ErrorException.class, () -> productService.retrieveSellerByProduct(product));
	}

	@Test
	void givenSellerProductWithProducts_whenRetrieveSellerByProduct_ThenRetrieveSellerFromRepository() throws ErrorException {
		List<Seller> sellers = new ArrayList<>();
		sellers.add(seller);
		Mockito.lenient().when(sellerRepository.retrieveSeller(Mockito.anyList())).thenReturn(sellers);

		productService.retrieveSellerByProduct(product);

		Mockito.verify(sellerRepository, Mockito.times(1)).retrieveSeller(Mockito.anyList());
	}
}