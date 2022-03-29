package ulaval.glo2003.floppa.product.repository.mongo;

import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MorphiaFilterProductFactory {

	public List<Filter> createConditionsProductFunction(ConditionProductDto conditionProductDto) {
		List<Filter> productConditions = new ArrayList<>();
		this.addProductIdCondition(conditionProductDto.getProductId(), productConditions);
		this.addProductTitleCondition(conditionProductDto.getTitle(), productConditions);
		this.addCategoriesCondition(conditionProductDto.getProductCategories(), productConditions);
		this.addMinPriceCondition(conditionProductDto.getMinPrice(), productConditions);
		this.addMaxPriceCondition(conditionProductDto.getMaxPrice(), productConditions);
		return productConditions;
	}

	private void addProductIdCondition(String id, List<Filter> productConditions) {
		Optional.ofNullable(id).ifPresent(productId -> productConditions.add(Filters.eq(ProductMapping.ID, productId)));
	}

	private void addProductTitleCondition(String title, List<Filter> productConditions){
		Optional.ofNullable(title).ifPresent(val -> productConditions.add(Filters.regex(ProductMapping.TITLE).pattern(title).caseInsensitive()));
	}

	private void addCategoriesCondition(List<ProductCategory> productCategories, List<Filter> productConditions) {
		Optional.ofNullable(productCategories)
				.filter(categories -> !categories.isEmpty())
				.ifPresent(val -> productConditions
						.add(Filters.in(ProductMapping.OFFERS, productCategories)));
	}

	private void addMinPriceCondition(Double minPrice, List<Filter> productConditions) {
		Optional.ofNullable(minPrice).ifPresent(val ->productConditions.add(Filters.gte(ProductMapping.SUGGESTED_PRICE, minPrice)));
	}

	private void addMaxPriceCondition(Double maxPrice, List<Filter> productConditions) {
		Optional.ofNullable(maxPrice).ifPresent(val ->productConditions.add(Filters.lte(ProductMapping.SUGGESTED_PRICE, maxPrice)));
	}
}