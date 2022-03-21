package ulaval.glo2003.floppa.product.applicative;

import ulaval.glo2003.floppa.product.domain.ProductCategory;

import java.util.List;

public class ProductDto {
	private final String title;
	private final String description;
	private final Double suggestedPrice;
	private final List<ProductCategory> categories;

	public ProductDto(String title, String description, Double suggestedPrice, List<ProductCategory> categories) {
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.categories = categories;
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Double getSuggestedPrice() {
		return suggestedPrice;
	}

	public List<ProductCategory> getCategories() {
		return categories;
	}
}
