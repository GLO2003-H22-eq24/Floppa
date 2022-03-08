package ulaval.glo2003.floppa.seller.applicative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class ConditionBuilderSellerTest {
	private String sellerName = "name";
	private String sellerBio = "bio";
	private final Seller seller = new Seller(sellerName, sellerBio, LocalDate.of(1995, 12, 12));

	ConditionBuilderSellerTest() throws ErrorException {
	}

	@Test
	void givenSellerId_whenAddSellerIdCondition_thenConditionEqualToOtherSellerId() {
		String sellerId = seller.getId();

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addSellerIdCondition(sellerId).build();

		Assertions.assertTrue(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}

	@Test
	void givenNotSellerId_whenAddSellerIdCondition_thenConditionNotEqualToOtherSellerId() {
		String sellerId = "notId";

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addSellerIdCondition(sellerId).build();

		Assertions.assertFalse(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}

	@Test
	void givenNullSellerId_whenAddSellerIdCondition_thenConditionEqualToOtherSellerId() {
		String sellerId = null;

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addSellerIdCondition(sellerId).build();

		Assertions.assertTrue(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}

	@Test
	void givenSellerWithProducts_whenAddProductFilterCondition_thenConditionEqualToOtherSeller() {
		List<Function<Product, Boolean>> productCondition = List.of(otherProduct -> true);
		seller.getProducts().add(Mockito.mock(Product.class));

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addProductFilterCondition(productCondition).build();

		Assertions.assertTrue(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}

	@Test
	void givenSellerWithNoProducts_whenAddProductFilterCondition_thenConditionNotEqualToOtherSeller() {
		List<Function<Product, Boolean>> productCondition = List.of(otherProduct -> true);

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addProductFilterCondition(productCondition).build();

		Assertions.assertFalse(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}

	@Test
	void givenNotSellerWithProducts_whenAddProductFilterCondition_thenConditionNotEqualToOtherSeller() {
		List<Function<Product, Boolean>> productCondition = List.of(otherProduct -> true, otherProduct -> false);
		seller.getProducts().add(Mockito.mock(Product.class));

		List<Function<Seller, Boolean>> sellerConditions = new ConditionBuilderSeller().addProductFilterCondition(productCondition).build();

		Assertions.assertFalse(sellerConditions.stream().findFirst().map(condition -> condition.apply(seller)).orElse(false));
	}
}