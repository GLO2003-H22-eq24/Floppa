package ulaval.glo2003.floppa.product.domain;

import java.util.List;

public class ConditionProductDto {
	private final String title;
	private final List<ProductCategory> productCategories;
	private final Double minPrice;
	private final Double maxPrice;
	private final String productId;

	public ConditionProductDto(String title, List<ProductCategory> productCategories, Double minPrice, Double maxPrice, String productId) {
		this.title = title;
		this.productCategories = productCategories;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public String getProductId() {
		return productId;
	}
}
