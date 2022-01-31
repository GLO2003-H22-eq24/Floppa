package ulaval.glo2003.floppa.product.domain;

import ulaval.glo2003.floppa.offers.domain.Offers;

import java.time.LocalTime;
import java.util.List;

public class Product {
	private String id;
	private LocalTime createdDate;
	private String title;
	private String description;
	private Double suggestedPrice;
	private List<Offers> offers;

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
