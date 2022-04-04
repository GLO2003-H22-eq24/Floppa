package ulaval.glo2003.floppa.offers.domain;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Property;

import java.time.LocalDateTime;

import static ulaval.glo2003.floppa.offers.repository.mongo.OffersMapping.*;

@Entity
public class Offers {
	@Property(value = CREATED_DATE)
	private LocalDateTime createdDate;
	@Property(value = ID)
	private String id;
	@Property(value = NAME)
	private String name;
	@Property(value = EMAIL)
	private Email email;
	@Property(value = PHONE_NUMBER)
	private PhoneNumber phoneNumber;
	@Property(value = MESSAGE)
	private String message;
	@Property(value = OFFER_AMOUNT)
	private Double offerAmount;

	public Offers(String name, Email email, PhoneNumber phoneNumber, String message, Double offerAmount, String id, LocalDateTime createdDate) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.message = message;
		this.offerAmount = offerAmount;
		this.id = id;
		this.createdDate = createdDate;
	}

	public Double getOfferAmount() {
		return offerAmount;
	}

	public String getName() {
		return name;
	}

	public Email getEmail() {
		return email;
	}

	public PhoneNumber getPhoneNumber() {
		return phoneNumber;
	}

	public String getMessage() {
		return message;
	}

	public String getId() {
		return id;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
}
