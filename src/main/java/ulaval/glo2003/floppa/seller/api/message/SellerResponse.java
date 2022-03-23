package ulaval.glo2003.floppa.seller.api.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import ulaval.glo2003.floppa.product.api.message.ProductResponse;

import java.time.LocalTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse {
	private String id;
	private String name;
	private LocalTime createdAt;
	private String bio;
	private List<ProductResponse> products;

	public SellerResponse() {
	}

	public SellerResponse(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public SellerResponse(String id, String name, LocalTime createdAt, String bio, List<ProductResponse> products) {
		this.id = id;
		this.name = name;
		this.createdAt = createdAt;
		this.bio = bio;
		this.products = products;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalTime getCreatedAt() { //used for serialisation
		return createdAt;
	}

	public String getBio() {
		return bio;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

}