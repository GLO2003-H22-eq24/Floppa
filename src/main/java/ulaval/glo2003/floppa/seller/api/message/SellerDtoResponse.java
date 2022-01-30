package ulaval.glo2003.floppa.seller.api.message;

import jakarta.json.bind.annotation.JsonbNillable;
import ulaval.glo2003.floppa.product.api.ProductDto;

import java.time.LocalTime;
import java.util.List;

@JsonbNillable
public class SellerDtoResponse {
	private String id;
	private String name;
	private LocalTime createdAt;
	private String bio;
	private List<ProductDto> products;

	public SellerDtoResponse() {
	}

	public SellerDtoResponse(String id, String name, LocalTime createdAt, String bio, List<ProductDto> products) {
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

	public LocalTime getCreatedAt() {
		return createdAt;
	}

	public String getBio() {
		return bio;
	}

	public List<ProductDto> getProducts() {
		return products;
	}
}
