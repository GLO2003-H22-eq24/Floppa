package ulaval.glo2003.floppa.product.domain;

import java.util.ArrayList;
import java.util.List;

public class ConditionProductDtoBuilder {
	private String title;
	private List<ProductCategory> productCategories = new ArrayList<>();
	private Double minPrice;
	private Double maxPrice;
	private String productId;

	public ConditionProductDtoBuilder addTitle(String title) {
		this.title = title;
		return this;
	}

	public ConditionProductDtoBuilder addProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
		return this;
	}

	public ConditionProductDtoBuilder addMinPrice(Double minPrice) {
		this.minPrice = minPrice;
		return this;
	}

	public ConditionProductDtoBuilder addMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
		return this;
	}

	public ConditionProductDtoBuilder addProductId(String productId) {
		this.productId = productId;
		return this;
	}

	public ConditionProductDto build(){
		return new ConditionProductDto(title, productCategories, minPrice, maxPrice, productId);
	}

}
