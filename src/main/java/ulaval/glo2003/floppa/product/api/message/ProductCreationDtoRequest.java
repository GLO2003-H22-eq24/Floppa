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

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getSuggestedPrice() {
		return suggestedPrice;
	}

	public void setSuggestedPrice(Double suggestedPrice) {
		this.suggestedPrice = suggestedPrice;
	} //used for serialization

	public List<String> getCategories() {
		return categories;
	}

	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
}
