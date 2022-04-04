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

	private static String offersValidName = "name";
	private static String offersValidEmail = "name";
	private static String offersValidPhoneNumber = "18198900989";
	private static Double offersValidAmount = 48.23;
	private static String offersValidMessage = "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.";

	private Double savedSuggestedPrice = 22.32;
	private String productId;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller("sellerName", "sellerBio", "2000-12-25"));
		productId = getProductIdByLocation(createProduct("productTitle", "productDescription", savedSuggestedPrice, List.of("sports", "housing"), savedSellerId));
	}

	@Test
	void givenAllValidFields_whenCreateProduct_thenStatus200() throws JsonProcessingException {
		createOfferForProduct(productId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersValidAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(200);
	}

	@Test
	void givenInvalidOfferAmount_whenCreatingOffer_thenThrowExceptionINVALID_PARAMETER() throws JsonProcessingException {

		Double offersBadAmount = 10.0;

		createOfferForProduct(productId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersBadAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenInvalidOfferMessage_whenCreatingOffer_thenThrowExceptionINVALID_PARAMETER() throws JsonProcessingException {

		String offersBadMessage = "Message trop court";

		createOfferForProduct(productId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersValidAmount, offersBadMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenNullId_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		String offersNullId = null;

		createOfferForProduct(offersNullId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersValidAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);

	}

	@Test
	void givenNullAmount_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		Double offersNullAmount = null;

		createOfferForProduct(productId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersNullAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenNullMessage_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		String offersNullMessage = null;

		createOfferForProduct(productId, offersValidName, offersValidEmail, offersValidPhoneNumber, offersValidAmount, offersNullMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenNullName_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		String offersNullName = null;

		createOfferForProduct(productId, offersNullName, offersValidEmail, offersValidPhoneNumber, offersValidAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenNullEmail_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		String offersNullEmail = null;

		createOfferForProduct(productId, offersValidName, offersNullEmail, offersValidPhoneNumber, offersValidAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);
	}

	@Test
	void givenNullPhoneNumber_whenCreatingOffer_thenThrowExceptionMISSING_PARAMETER() throws JsonProcessingException {

		String offersNullPhoneNumber = null;

		createOfferForProduct(productId, offersValidName, offersValidEmail, offersNullPhoneNumber, offersValidAmount, offersValidMessage)
				.then()
				.assertThat()
				.statusCode(400);
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
