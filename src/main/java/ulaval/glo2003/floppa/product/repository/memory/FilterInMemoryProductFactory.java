package ulaval.glo2003.floppa.product.repository.memory;

import org.apache.commons.lang3.StringUtils;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class FilterInMemoryProductFactory {

	public List<Function<Product, Boolean>> createConditionsProductFunction(ConditionProductDto conditionProductDto) {
		List<Function<Product, Boolean>> productConditions = new ArrayList<>();
		Optional.ofNullable(conditionProductDto).ifPresentOrElse(conditions -> {
			this.addProductIdCondition(conditionProductDto.getProductId(), productConditions);
			this.addProductTitleCondition(conditionProductDto.getTitle(), productConditions);
			this.addCategoriesCondition(conditionProductDto.getProductCategories(), productConditions);
			this.addMinPriceCondition(conditionProductDto.getMinPrice(), productConditions);
			this.addMaxPriceCondition(conditionProductDto.getMaxPrice(), productConditions);
			this.addDefaultTrueCondition(productConditions);
		}, () -> this.addDefaultTrueCondition(productConditions));
		return productConditions;
	}

	private void addProductIdCondition(String id, List<Function<Product, Boolean>> productConditions) {
		Optional.ofNullable(id).ifPresent(productId -> productConditions.add(otherProduct -> Objects.equals(id, otherProduct.getId())));
	}

	private void addProductTitleCondition(String title, List<Function<Product, Boolean>> productConditions){
		Optional.ofNullable(title).ifPresent(val -> productConditions.add( otherProduct -> StringUtils.containsIgnoreCase(otherProduct.getTitle(), title)));
	}

	private void addCategoriesCondition(List<ProductCategory> productCategories, List<Function<Product, Boolean>> productConditions) {
		Optional.ofNullable(productCategories)
				.filter(categories -> !categories.isEmpty())
				.ifPresent(val ->productConditions.add( otherProduct -> productCategories.stream()
						.anyMatch(category -> otherProduct.getCategories().contains(category))));
	}

	private void addMinPriceCondition(Double minPrice, List<Function<Product, Boolean>> productConditions) {
		Optional.ofNullable(minPrice).ifPresent(val ->productConditions.add( otherProduct -> minPrice <= otherProduct.getSuggestedPrice()));
	}

	private void addMaxPriceCondition(Double maxPrice, List<Function<Product, Boolean>> productConditions) {
		Optional.ofNullable(maxPrice).ifPresent(val ->productConditions.add( otherProduct -> maxPrice >= otherProduct.getSuggestedPrice()));
	}

	private void addDefaultTrueCondition(List<Function<Product, Boolean>> productConditions) {
		if (productConditions.isEmpty()) {
			productConditions.add(otherProduct -> true);
		}
	}
}
