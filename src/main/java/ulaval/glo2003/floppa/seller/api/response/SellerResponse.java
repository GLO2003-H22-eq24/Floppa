package ulaval.glo2003.floppa.seller.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SellerResponse {
	private String id;
	private String name;
	private String createdAt;
	private String bio;
	private String birthDate;
	private List<ProductResponse> products;

	public SellerResponse() {
	}

	public SellerResponse(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public SellerResponse(String id, String name, String createdAt, String bio, List<ProductResponse> products) {
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

	public String getCreatedAt() { //used for serialisation
		return createdAt;
	}

	public String getBio() {
		return bio;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public List<ProductResponse> getProducts() {
		return products;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
}
