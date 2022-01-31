package ulaval.glo2003.floppa.product.api;

import jakarta.json.bind.annotation.JsonbNillable;
import ulaval.glo2003.floppa.offers.api.OffersDto;

import java.time.LocalTime;

@JsonbNillable
public class ProductDto {
	private String id;
	private LocalTime createdAt;
	private String title;
	private String description;
	private Double suggestedPrice;
	private OffersDto offers;

	public ProductDto() {
	}

	public ProductDto(String id, LocalTime createdAt, String title, String description, Double suggestedPrice, OffersDto offers) {
		this.id = id;
		this.createdAt = createdAt;
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.offers = offers;
	}

	public String getId() {
		return id;
	}

	public LocalTime getCreatedAt() {
		return createdAt;
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

	public OffersDto getOffers() {
		return offers;
	}
}
