package ulaval.glo2003.floppa.offers.api.message;

import jakarta.json.bind.annotation.JsonbProperty;

public class OffersDto {
	@JsonbProperty(nillable = true)
	private Double mean;
	private int count;

	public OffersDto() { //Used for serialization
	}

	public OffersDto(Double mean, int count) {
		this.mean = mean;
		this.count = count;
	}

	public int getCount() {
		return count;
	}
}
