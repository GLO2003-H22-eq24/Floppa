package ulaval.glo2003.floppa.offers.applicative;

import ulaval.glo2003.floppa.offers.domain.Email;
import ulaval.glo2003.floppa.offers.domain.PhoneNumber;

public class OffersDto {
	private final String name;
	private final Email email;
	private final PhoneNumber phoneNumber;
	private final Double amount;
	private final String message;

	public OffersDto(String name, Email email, PhoneNumber phoneNumber, Double amount, String message) {
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.amount = amount;
		this.message = message;
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

	public Double getAmount() {
		return amount;
	}

	public String getMessage() {
		return message;
	}
}
