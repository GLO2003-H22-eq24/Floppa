package ulaval.glo2003.floppa.offers.api.request;

public class OffersRequest {
	private String name;
	private String email;
	private String phoneNumber;
	private Double amount;
	private String message;

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public Double getAmount() {
		return amount;
	}

	public String getMessage() {
		return message;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
