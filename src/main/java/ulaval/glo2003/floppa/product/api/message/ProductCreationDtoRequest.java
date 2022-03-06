package ulaval.glo2003.floppa.product.api.message;
import jakarta.json.bind.annotation.JsonbProperty;

import java.util.List;

public class ProductCreationDtoRequest {
	private String title;
	private String description;
	private String birthDate;
	private int suggestedPrice;
	@JsonbProperty(nillable = true)
	private List<String> categories;
}
