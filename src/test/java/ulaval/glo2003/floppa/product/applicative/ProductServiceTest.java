package ulaval.glo2003.floppa.product.applicative;

import com.google.common.base.Supplier;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@Mock
	private SellerRepository sellerRepository;
	@Mock
	private ConditionSellerAssembleur conditionSellerAssembleur;
	@Mock
	private ProductFactory productFactory;
	@InjectMocks
	private ProductService productService;
	@Mock
	private ProductDto anyProductDto;
	@Mock
	private Product anyProduct;
	@Mock
	private Seller anySeller;
	@Mock
	private ConditionProductDto anyConditionProductDto;
	private final String anySellerId = "id";

	@Test
	void givenSellerIdAndProductDto_whenCreateProductForSeller_thenProductInProducts() throws ErrorException {
		List<Product> products = new ArrayList<>();
		Mockito.when(productFactory.createProduct(anyProductDto)).thenReturn(anyProduct);
		Mockito.when(sellerRepository.retrieveSeller(anySellerId)).thenReturn(anySeller);
		Mockito.when(anySeller.getProducts()).thenReturn(products);

		productService.createProductForSeller(anySellerId, anyProductDto);

		Assertions.assertTrue(products.contains(anyProduct));
	}

	@Test
	void givenSellerIdAndProductDto_whenRetrieveOneProductWithConditions_thenProductInProducts() {
		Mockito.when(sellerRepository.findProducts(Mockito.any())).thenReturn(new ArrayList<>());

		Executable productSupplier = () -> productService.retrieveOneProductWithConditions(anyConditionProductDto);

		Assertions.assertThrows(ErrorException.class, productSupplier);
	}
}