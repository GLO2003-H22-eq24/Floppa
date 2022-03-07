package ulaval.glo2003.floppa.product.api.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.json.bind.annotation.JsonbNillable;
import jakarta.json.bind.annotation.JsonbProperty;
import ulaval.glo2003.floppa.offers.api.OffersDto;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

import java.time.LocalTime;
import java.util.List;

@JsonbNillable
public class ProductDtoResponse {
	private String id;
	private LocalTime createdAt;
	private String title;
	private String description;
	private Double suggestedPrice;
	private OffersDto offers;
	private List<String> categories;
	@JsonbProperty(nillable = true)
	@JsonIgnoreProperties
	private SellerDtoResponse seller;
	public ProductDtoResponse() {
	}

	public ProductDtoResponse(String id, LocalTime createdAt, String title, String description, Double suggestedPrice, OffersDto offers, List<String> categories) {
		this.id = id;
		this.createdAt = createdAt;
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.offers = offers;
		this.categories = categories;
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

	public List<String> getCategories() {
		return categories;
	}

	public SellerDtoResponse getSeller() {
		return seller;
	}

	public void setSellerDtoResponse(SellerDtoResponse seller) {
		this.seller = seller;
	}
}
