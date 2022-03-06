package ulaval.glo2003.floppa.product.api.message;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class ProductCreationDtoRequest {
	private String title;
	private String description;
	private Double suggestedPrice;
	@JsonbProperty(nillable = true)
	private List<String> categories;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Double getSuggestedPrice() {
		return suggestedPrice;
	}

	public List<String> getCategories() {
		return categories;
	}
}
