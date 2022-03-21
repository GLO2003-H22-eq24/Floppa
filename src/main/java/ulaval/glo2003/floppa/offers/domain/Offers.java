package ulaval.glo2003.floppa.offers.domain;

import org.mongodb.morphia.annotations.Embedded;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import java.time.LocalTime;

@Embedded
public class Offers {
	private LocalTime createdDate;
	private String id;
	private String name;
	private Email email;
	private PhoneNumber phoneNumber;
	private String message;
	private Double offerAmount;

	public Offers(String name, Email email, PhoneNumber phoneNumber, String message, Double offerAmount, String id, LocalTime createdDate) throws ErrorException {
		if (message.length() < 100){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.message = message;
		this.offerAmount = offerAmount;
		this.id = id;
		this.createdDate = createdDate;
	}

	public Offers(Double offerAmount) {
		this.offerAmount = offerAmount;
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

	public LocalTime getCreatedDate() {
		return createdDate;
	}
}
