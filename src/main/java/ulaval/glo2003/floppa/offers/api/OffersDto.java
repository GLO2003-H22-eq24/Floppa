package ulaval.glo2003.floppa.offers.api;

import jakarta.json.bind.annotation.JsonbProperty;

public class OffersDto {
	@JsonbProperty(nillable = true)
	private Double mean;
	private int count;

	public OffersDto() {
	}

	public OffersDto(Double mean, int count) {
		this.mean = mean;
		this.count = count;
	}

	public Double getMean() {
		return mean;
	}

	public int getCount() {
		return count;
	}
}
