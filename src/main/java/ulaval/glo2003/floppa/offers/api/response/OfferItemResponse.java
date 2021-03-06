package ulaval.glo2003.floppa.offers.api.response;


public class OfferItemResponse {
	private String id;
	private String createdAt;
	private Double amount;
	private String message;
	private BuyerResponse buyer;

	public OfferItemResponse() {
	}

	public OfferItemResponse(String id, String createdAt, Double amount, String message, BuyerResponse buyer) {
		this.id = id;
		this.createdAt = createdAt;
		this.amount = amount;
		this.message = message;
		this.buyer = buyer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BuyerResponse getBuyer() {
		return buyer;
	}

	public void setBuyer(BuyerResponse buyer) {
		this.buyer = buyer;
	}
}
