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

	@Test
	void givenInvalidOfferAmount_whenCreatingOffer_thenThrowExceptionINVALID_PARAMETER() throws JsonProcessingException {
		//TODO si montant de l'offre est inferieur au montant suggerer du produit, lancer INVALID_PARAMETER
	}

	@Test
	void givenInvalidOfferMessage_whenCreatingOffer_thenThrowExceptionINVALID_PARAMETER() throws JsonProcessingException {
		//TODO si le message est de moins de 100 characteres, lancer INVALID_PARAMETER
	}

	@Test
	void givenNullId_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {
		//TODO si l'id est null, lancer MISSING_PARAMETER

	}

	@Test
	void givenNullCreatedAt_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {
		//TODO si le created at est null, lancer MISSING_PARAMETER
	}

	@Test
	void givenNullAmount_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {
		//TODO si le amount est null, lancer MISSING_PARAMETER
	}

	@Test
	void givenNullMessage_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {
		//TODO si le message est null, lancer MISSING_PARAMETER
	}

	@Test
	void givenNullBuyer_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {
		//TODO si le buyer est null, lancer MISSING_PARAMETER
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
