package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.response.ProductViewResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.getProductIdByLocation;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceAddViewITTest extends ServerTestIT {
	private String productId;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller("sellerName", "sellerBio", "2000-12-25"));
		Double savedSuggestedPrice = 22.32;
		productId = getProductIdByLocation(createProduct("productTitle", "productDescription", savedSuggestedPrice, List.of("sports", "housing"), savedSellerId));
	}

	@Test
	void givenProduct_whenAddViews_thenStatus200() {
		addViewForProduct(productId)
				.then()
				.assertThat()
				.statusCode(200);
	}

	public static io.restassured.response.Response addViewForProduct(String productId) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.port(PORT)
				.post(String.format("/products/%s/views", productId));
	}
}
