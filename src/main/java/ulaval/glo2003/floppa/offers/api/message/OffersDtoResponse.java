package ulaval.glo2003.floppa.offers.api.message;

import jakarta.json.bind.annotation.JsonbProperty;

public class OffersDtoResponse {
	@JsonbProperty(nillable = true)
	private Double mean;
	private int count;

	public OffersDtoResponse() { //Used for serialization
	}

	public OffersDtoResponse(Double mean, int count) {
		this.mean = mean;
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
