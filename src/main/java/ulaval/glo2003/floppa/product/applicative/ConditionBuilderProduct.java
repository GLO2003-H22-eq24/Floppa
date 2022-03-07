package ulaval.glo2003.floppa.product.applicative;

import org.apache.commons.lang3.StringUtils;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class ConditionBuilderProduct {
	private final List<Function<Product, Boolean>> productConditions = new ArrayList<>();

	public ConditionBuilderProduct addProductIdCondition(String id) {
		Optional.ofNullable(id).ifPresent(productId -> this.productConditions.add(otherProduct -> Objects.equals(id, otherProduct.getId())));
		return this;
	}

	public ConditionBuilderProduct addProductTitleCondition(String title){
		Optional.ofNullable(title).ifPresent(val -> this.productConditions.add( otherProduct -> StringUtils.containsIgnoreCase(otherProduct.getTitle(), title)));
		return this;
	}

	public ConditionBuilderProduct addCategoriesCondition(List<ProductCategory> productCategories) {
		Optional.ofNullable(productCategories)
				.filter(categories -> !categories.isEmpty())
				.ifPresent(val ->this.productConditions.add( otherProduct -> productCategories.stream()
						.anyMatch(category -> otherProduct.getCategories().contains(category))));
		return this;
	}

	public ConditionBuilderProduct addMinPriceCondition(Double minPrice) {
		Optional.ofNullable(minPrice).ifPresent(val ->this.productConditions.add( otherProduct -> minPrice <= otherProduct.getSuggestedPrice()));
		return this;
	}

	public ConditionBuilderProduct addMaxPriceCondition(Double maxPrice) {
		Optional.ofNullable(maxPrice).ifPresent(val ->this.productConditions.add( otherProduct -> maxPrice >= otherProduct.getSuggestedPrice()));
		return this;
	}

	private void addDefaultTrueCondition() {
		this.productConditions.add(otherProduct -> true);
	}

	public List<Function<Product, Boolean>> build() {
		if (this.productConditions.isEmpty()){
			this.addDefaultTrueCondition();
		}
		return this.productConditions;
	}
}
