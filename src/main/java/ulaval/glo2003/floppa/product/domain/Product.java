package ulaval.glo2003.floppa.product.domain;

import org.mongodb.morphia.annotations.Embedded;
import org.mongodb.morphia.annotations.Id;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Embedded
public class Product {
	@Id
	private final String id;
	private final LocalTime createdDate;
	private String title;
	private String description;
	private Double suggestedPrice;
	private List<ProductCategory> categories;
	@Embedded
	private List<Offers> offers;

	public Product(String title, String description, Double suggestedPrice, List<ProductCategory> categories) throws ErrorException {
		this.validatePrice(suggestedPrice);
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.categories = categories;
		this.offers = new ArrayList<>();
		this.id = UUID.randomUUID().toString();
		this.createdDate = LocalTime.now(Clock.system(ZoneId.of("-05:00")));
	}

	private void validatePrice(Double suggestedPrice) throws ErrorException {
		if (suggestedPrice < 1){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}

	public Double computeMeanOffers(){
		return this.computeNumberOfOffers() != 0 ? this.offers.stream().map(Offers::getOfferAmount).reduce(0.00, Double::sum) / this.computeNumberOfOffers(): null;
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

	public List<ProductCategory> getCategories() {
		return categories;
	}

	public void addOffer(Offers offers) throws ErrorException {
		if (offers.getOfferAmount() < this.suggestedPrice){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.offers.add(offers);
	}
}
