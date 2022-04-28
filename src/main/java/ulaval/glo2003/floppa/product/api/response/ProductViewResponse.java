package ulaval.glo2003.floppa.product.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductViewResponse {
	private String productId;
	private Integer views;

	public ProductViewResponse() {
	}

	public ProductViewResponse(String productId, Integer views) {
		this.productId = productId;
		this.views = views;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public Integer getViews() {
		return views;
	}

	public void setViews(Integer views) {
		this.views = views;
	}
}
