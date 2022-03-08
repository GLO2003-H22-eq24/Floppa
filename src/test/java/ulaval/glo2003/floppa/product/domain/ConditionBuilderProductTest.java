package ulaval.glo2003.floppa.product.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.applicative.ConditionBuilderProduct;

import java.util.List;
import java.util.function.Function;

@ExtendWith(MockitoExtension.class)
class ConditionBuilderProductTest {
	private String title = "title";
	private List<ProductCategory> productCategories = List.of(ProductCategory.APPAREL, ProductCategory.BEAUTY);
	private Double price = 55.12;
	private Product product = new Product(title, "description", price, productCategories);

	ConditionBuilderProductTest() throws ErrorException {
	}

	@Test
	void givenProductId_whenAddProductIdCondition_thenConditionEqualToOtherProductId() {
		String productId = product.getId();

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductIdCondition(productId).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNotProductId_whenAddProductIdCondition_thenConditionNotEqualToOtherProductId() {
		String productId = "notId";

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductIdCondition(productId).build();

		Assertions.assertFalse(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNoProductId_whenAddProductIdCondition_thenConditionEqualToOtherProductId() {
		String productId = null;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductIdCondition(productId).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenTitle_whenAddProductTitleCondition_thenConditionEqualToOtherProductTitle() {
		String title = this.title;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductTitleCondition(title).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenTitleUpperCase_whenAddProductTitleCondition_thenConditionEqualToOtherProductTitle() {
		String title = this.title.toUpperCase();

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductTitleCondition(title).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenTitleContained_whenAddProductTitleCondition_thenConditionEqualToOtherProductTitle() {
		String titleContained = title.substring(2);

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductTitleCondition(titleContained).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNotTitle_whenAddProductTitleCondition_thenConditionNotEqualToOtherProductTitle() {
		String otherTitle = "notTitle";

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductTitleCondition(otherTitle).build();

		Assertions.assertFalse(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNullTitle_whenAddProductTitleCondition_thenConditionEqualToOtherProductTitle() {
		String title = null;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addProductTitleCondition(title).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenCategories_whenAddCategoriesCondition_thenConditionEqualToOtherProductCategories() {
		List<ProductCategory> productCategories = product.getCategories();

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addCategoriesCondition(productCategories).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNotCategories_whenAddCategoriesCondition_thenConditionNotEqualToOtherProductCategories() {
		List<ProductCategory> productCategories = List.of(ProductCategory.HOUSING);

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addCategoriesCondition(productCategories).build();

		Assertions.assertFalse(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenEmptyCategories_whenAddCategoriesCondition_thenConditionEqualToOtherProductCategories() {
		List<ProductCategory> productCategories = List.of();

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addCategoriesCondition(productCategories).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}


	@Test
	void givenMinPrice_whenAddMinPriceCondition_thenConditionEqualToOtherProductPrice() {
		Double price = product.getSuggestedPrice() - 1;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMinPriceCondition(price).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNotMinPrice_whenAddMinPriceCondition_thenConditionNotEqualToOtherProductPrice() {
		Double price = product.getSuggestedPrice() + 1;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMinPriceCondition(price).build();

		Assertions.assertFalse(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNullMinPrice_whenAddMinPriceCondition_thenConditionEqualToOtherProductPrice() {
		Double price = null;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMinPriceCondition(price).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}


	@Test
	void givenMaxPrice_whenAddMinPriceCondition_thenConditionEqualToOtherProductPrice() {
		Double price = product.getSuggestedPrice() + 1;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMaxPriceCondition(price).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNotMaxPrice_whenAddMinPriceCondition_thenConditionNotEqualToOtherProductPrice() {
		Double price = product.getSuggestedPrice() - 1;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMaxPriceCondition(price).build();

		Assertions.assertFalse(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}

	@Test
	void givenNullMaxPrice_whenAddMinPriceCondition_thenConditionEqualToOtherProductPrice() {
		Double price = null;

		List<Function<Product, Boolean>> productConditions = new ConditionBuilderProduct().addMaxPriceCondition(price).build();

		Assertions.assertTrue(productConditions.stream().findFirst().map(condition -> condition.apply(product)).orElse(false));
	}
}