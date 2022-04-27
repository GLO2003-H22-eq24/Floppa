package ulaval.glo2003.floppa.product.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ulaval.glo2003.floppa.offers.api.response.OffersResponse;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductResponse {
	private String id;
	private String createdAt;
	private String title;
	private String description;
	private Double suggestedPrice;
	private OffersResponse offers;
	private List<String> categories;
	private SellerResponse seller;
	public ProductResponse() {
	}

	public ProductResponse(String id, String createdAt, String title, String description, Double suggestedPrice, OffersResponse offers, List<String> categories) {
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

	public String getCreatedAt() {
		return createdAt;
	} //used for serialization

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public Double getSuggestedPrice() {
		return suggestedPrice;
	}

	public OffersResponse getOffers() {
		return offers;
	}

	public List<String> getCategories() {
		return categories;
	}

	public SellerResponse getSeller() {
		return seller;
	}

	public void setSellerDtoResponse(SellerResponse seller) {
		this.seller = seller;
	}
}
