package ulaval.glo2003.floppa.offers.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

public class Offers {
	private String name;
	private Email email;
	private PhoneNumber phoneNumber;
	private String message;
	private Double offerAmount;

	public Offers(String name, Email email, PhoneNumber phoneNumber, String message, Double offerAmount) throws ErrorException {
		if (message.length() < 100){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.message = message;
		this.offerAmount = offerAmount;
	}

	public Offers(Double offerAmount) {
		this.offerAmount = offerAmount;
	}

	public Double getOfferAmount() {
		return offerAmount;
	}
}
