package ulaval.glo2003.floppa.product.domain;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.time.LocalDateTime;
import java.util.*;

import static ulaval.glo2003.floppa.product.repository.mongo.ProductMapping.*;

@Entity
public class Product {
	@Property(value = ID)
	private final String id;
	@Property(value = CREATED_DATE)
	private final LocalDateTime createdDate;
	@Property(value = TITLE)
	private String title;
	@Property(value = DESCRIPTION)
	private String description;
	@Property(value = SUGGESTED_PRICE)
	private Double suggestedPrice;
	@Property(value = CATEGORIES)
	private List<ProductCategory> categories;
	@Property(value = OFFERS)
	private List<Offers> offers;

	public Product(String title, String description, Double suggestedPrice, List<ProductCategory> categories, String id, LocalDateTime createdDate) {
		this.title = title;
		this.description = description;
		this.suggestedPrice = suggestedPrice;
		this.categories = categories;
		this.offers = new ArrayList<>();
		this.id = id;
		this.createdDate = createdDate;
	}

	public Double computeMeanOffers(){
		return this.computeNumberOfOffers() != 0 ? getOffers().stream().map(Offers::getOfferAmount).reduce(0.00, Double::sum) / this.computeNumberOfOffers(): null;
	}

	public int computeNumberOfOffers(){
		return getOffers().size();
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
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
		Optional.ofNullable(this.offers).orElseGet(() -> this.offers = new ArrayList<>());
		return offers;
	}

	public List<ProductCategory> getCategories() {
		Optional.ofNullable(this.categories).orElseGet(() -> this.categories = new ArrayList<>());
		return categories;
	}

	public void addOffer(Offers offers) throws ErrorException {
		if (offers.getOfferAmount() < this.suggestedPrice){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.getOffers().add(offers);
	}

	public Double computeMaxOffers() {
		return getOffers().stream()
				.max(Comparator.comparing(Offers::getOfferAmount))
				.map(Offers::getOfferAmount)
				.orElse(null);
	}

	public Double computeMinOffers() {
		return getOffers().stream()
				.min(Comparator.comparing(Offers::getOfferAmount))
				.map(Offers::getOfferAmount)
				.orElse(null);
	}
}
