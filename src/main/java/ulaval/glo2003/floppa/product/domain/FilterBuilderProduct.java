package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.seller.domain.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class FilterBuilderProduct {
	private final List<Function<Product, Boolean>> productConditions = new ArrayList<>();


	public FilterBuilderProduct addProductIdCondition(String id) {
		this.productConditions.add(otherProduct -> Objects.equals(id, otherProduct.getId()));
		return this;
	}

	public FilterBuilderProduct addProductTitleCondition(String title){
		this.productConditions.add( otherProduct -> Objects.equals(title, otherProduct.getTitle()));
		return this;
	}

	public FilterBuilderProduct addProductDescriptionCondition(String description) {
		this.productConditions.add( otherProduct -> Objects.equals(description, otherProduct.getDescription()));
		return this;
	}

	public FilterBuilderProduct addCategoriesCondition(List<ProductCategory> productCategories) {
		this.productConditions.add( otherProduct -> productCategories.stream().anyMatch(category -> otherProduct.getCategories().contains(category)));
		return this;
	}

	public FilterBuilderProduct addMinPriceCondition(Double minPrice) {
		this.productConditions.add( otherProduct -> minPrice <= otherProduct.getSuggestedPrice());
		return this;
	}

	public FilterBuilderProduct addMaxPriceCondition(Double maxPrice) {
		this.productConditions.add( otherProduct -> maxPrice >= otherProduct.getSuggestedPrice());
		return this;
	}

	public List<Function<Product, Boolean>> build() {
		return productConditions;
	}
}
