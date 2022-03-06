package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public class Product {
	private String id;
	private LocalTime createdDate;
	private String title;
	private String description;
	private Double suggestedPrice;
	private List<ProductCategory> categories;
	private List<Offers> offers;

	public Product(LocalTime createdDate, String title, String description, Double suggestedPrice, List<ProductCategory> categories, List<Offers> offers) throws ErrorException {
		this.validatePrice(suggestedPrice);
		this.createdDate = createdDate;
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.categories = categories;
		this.offers = offers;
		this.id = UUID.randomUUID().toString();
	}

	private void validatePrice(Double suggestedPrice) throws ErrorException {
		if (suggestedPrice < 1){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}

	public Double computeMeanOffers(){
		return this.offers.stream().map(Offers::getOfferAmount).reduce(0.00, Double::sum) / this.computeNumberOfOffers();
	}

	public int computeNumberOfOffers(){
		return this.offers.size();
	}

	public String getId() {
		return id;
	}

	public LocalTime getCreatedDate() {
		return createdDate;
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

	public List<Offers> getOffers() {
		return offers;
	}
}
