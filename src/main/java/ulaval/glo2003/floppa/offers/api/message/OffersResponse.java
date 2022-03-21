package ulaval.glo2003.floppa.offers.api.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OffersResponse {
	private Double mean;
	private int count;
	private Double min;
	private Double max;
	private List<OfferItemResponse> items;

	public OffersResponse() { //Used for serialization
	}

	public OffersResponse(Double mean, int count, Double min, Double max, List<OfferItemResponse> items) {
		this.mean = mean;
		this.count = count;
		this.min = min;
		this.max = max;
		this.items = items;
	}

	public OffersResponse(Double mean, int count) {
		this.mean = mean;
		this.count = count;
	}

	public Double getMean() {
		return mean;
	}

	public void setMean(Double mean) {
		this.mean = mean;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Double getMin() {
		return min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Double getMax() {
		return max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public List<OfferItemResponse> getItems() {
		return items;
	}

	public void setItems(List<OfferItemResponse> items) {
		this.items = items;
	}
}
