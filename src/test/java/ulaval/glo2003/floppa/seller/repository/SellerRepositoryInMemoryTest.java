package ulaval.glo2003.floppa.seller.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.Seller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class SellerRepositoryInMemoryTest {
	private Map<String, Seller> sellersById = new HashMap<>();
	@Mock
	private Seller seller;
	private String sellerId = "id";
	private String anyId="anyId";
	private SellerRepositoryInMemory sellerRepositoryInMemory;

	@BeforeEach
	void givenRepoWithSellersById() {
		sellersById.put(sellerId, seller);
		sellerRepositoryInMemory = new SellerRepositoryInMemory(sellersById);
	}

	@Test
	void givenNewSeller_whenSaveSeller_thenSellerIsAdded() {
		Seller newSeller = Mockito.mock(Seller.class);
		Mockito.when(newSeller.getId()).thenReturn(anyId);

		this.sellerRepositoryInMemory.saveSeller(newSeller);

		Assertions.assertTrue(this.sellersById.containsValue(newSeller));
	}

	@Test
	void givenSellerId_whenRetrieveSeller_thenSeller() throws ErrorException {

		Seller seller = this.sellerRepositoryInMemory.retrieveSeller(sellerId);

		Assertions.assertEquals(seller, this.seller);
	}

	@Test
	void givenNotSellerId_whenRetrieveSeller_thenErrorException() {
		String notSellerId = "notId";

		Assertions.assertThrows(ErrorException.class, () -> this.sellerRepositoryInMemory.retrieveSeller(notSellerId));
	}

	@Test
	void givenConditions_whenRetrieveSeller_thenSeller() {
		List<Function<Seller, Boolean>> sellerConditions = List.of(otherSeller -> true);

		List<Seller> sellers = this.sellerRepositoryInMemory.retrieveSeller(sellerConditions);

		Assertions.assertTrue(sellers.contains(this.seller));
	}

	@Test
	void givenNotConditions_whenRetrieveSeller_thenNoSeller() {
		List<Function<Seller, Boolean>> sellerConditions = List.of(otherSeller -> false);

		List<Seller> sellers = this.sellerRepositoryInMemory.retrieveSeller(sellerConditions);

		Assertions.assertFalse(sellers.contains(this.seller));
	}

	@Test
	void givenTrueConditionsAndSellerWithProduct_whenFindProducts_thenProductFromSeller() {
		Product product = Mockito.mock(Product.class);
		Mockito.when(seller.getProducts()).thenReturn(List.of(product));
		List<Function<Seller, Boolean>> sellerConditions = List.of(otherSeller -> true);
		List<Function<Product, Boolean>> productConditions = List.of(otherProduct -> true);

		List<Product> products = this.sellerRepositoryInMemory.findProducts(sellerConditions, productConditions);

		Assertions.assertTrue(products.contains(product));
	}

	@Test
	void givenTrueConditionsSellerAndNotProductAndSellerWithProduct_whenFindProducts_thenNotProductFromSeller() {
		Product product = Mockito.mock(Product.class);
		Mockito.when(seller.getProducts()).thenReturn(List.of(product));
		List<Function<Seller, Boolean>> sellerConditions = List.of(otherSeller -> true);
		List<Function<Product, Boolean>> productConditions = List.of(otherProduct -> false);

		List<Product> products = this.sellerRepositoryInMemory.findProducts(sellerConditions, productConditions);

		Assertions.assertFalse(products.contains(product));
	}
}