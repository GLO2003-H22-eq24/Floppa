package ulaval.glo2003.floppa.product.repository.mongo;

import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import dev.morphia.query.experimental.filters.RegexFilter;
import org.apache.commons.lang3.StringUtils;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

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
		Optional.ofNullable(id).ifPresent(productId -> productConditions.add(Filters.eq("id", productId)));
	}

	private void addProductTitleCondition(String title, List<Filter> productConditions){
		Optional.ofNullable(title).ifPresent(val -> productConditions.add(Filters.regex("title").pattern(title).caseInsensitive()));
	}

	private void addCategoriesCondition(List<ProductCategory> productCategories, List<Filter> productConditions) {
		Optional.ofNullable(productCategories)
				.filter(categories -> !categories.isEmpty())
				.ifPresent(val -> productConditions
						.add(Filters.in("offers", productCategories)));
	}

	private void addMinPriceCondition(Double minPrice, List<Filter> productConditions) {
		Optional.ofNullable(minPrice).ifPresent(val ->productConditions.add(Filters.gte("suggestedPrice", minPrice)));
	}

	private void addMaxPriceCondition(Double maxPrice, List<Filter> productConditions) {
		Optional.ofNullable(maxPrice).ifPresent(val ->productConditions.add(Filters.lte("suggestedPrice", maxPrice)));
	}
}
