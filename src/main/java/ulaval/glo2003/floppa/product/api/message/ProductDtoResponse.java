package ulaval.glo2003.floppa.product.api.message;

import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;
import ulaval.glo2003.floppa.offers.api.OffersDto;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

import java.time.LocalTime;

@JsonbNillable
public class ProductDtoResponse {
	private String id;
	private LocalTime createdAt;
	private String title;
	private String description;
	private Double suggestedPrice;
	private OffersDto offers;
	@JsonbProperty(nillable = true)
	private SellerDtoResponse sellerDtoResponse;
	public ProductDtoResponse() {
	}

	public ProductDtoResponse(String id, LocalTime createdAt, String title, String description, Double suggestedPrice, OffersDto offers) {
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

	public void setSellerDtoResponse(SellerDtoResponse sellerDtoResponse) {
		this.sellerDtoResponse = sellerDtoResponse;
	}
}
