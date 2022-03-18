package ulaval.glo2003.floppa.offers.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.getProductIdByLocation;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class OffersResourceCreateITTest extends ServerTestIT {

	private static String nameField = "name";
	private static String emailField = "email";
	private static String phoneNumberField = "phoneNumber";
	private static String amountField = "amount";
	private static String messageField = "message";

	private Double savedSuggestedPrice = 22.32;
	private String productId;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller("sellerName", "sellerBio", "2000-12-25"));
		productId = getProductIdByLocation(createProduct("productTitle", "productDescription", savedSuggestedPrice, List.of("sports", "housing"), savedSellerId));
	}

	@Test
	void givenAllValidFields_whenCreateProduct_thenStatus200() throws JsonProcessingException {
		createOfferForProduct(productId, "name", "asdb@asd.com", "18191234567", 48.23, "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.")
				.then()
				.assertThat()
				.statusCode(200);
	}

	public static io.restassured.response.Response createOfferForProduct(String productId, String name, String email, String phoneNumber, Double amount, String message) throws JsonProcessingException {
		Map<String, Object> body = new HashMap<>();
		body.put(nameField, name);
		body.put(emailField, email);
		body.put(phoneNumberField, phoneNumber);
		body.put(amountField, amount);
		body.put(messageField, message);
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.port(PORT)
				.body(new ObjectMapper().writeValueAsString(body)).post(String.format("/products/%s/offers", productId));
	}
}
