package ulaval.glo2003.floppa.seller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

import java.util.Arrays;
import java.util.List;

import static ulaval.glo2003.floppa.offers.api.OffersResourceCreateITTest.createOfferForProduct;
import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.getProductIdByLocation;

public class SellerResourceGetMeITTest extends ServerTestIT {

	private String currentSellerId;
	private String savedName = "name";
	private String savedBio = "bio";
	private String validBirthDate = "2000-12-25";
	private static String subPathSellerById = "/sellers/@me";

	@BeforeEach
	void givenSeller() throws JsonProcessingException {
		currentSellerId = SellerResourceCreateITTest.getSellerIdByLocation(SellerResourceCreateITTest.SaveSeller(savedName, savedBio, validBirthDate));
	}

	@Test
	void givenSellerId_whenGetSeller_thenStatus200() {
		retrieveSellerMe(currentSellerId).then().assertThat().statusCode(200);
	}

	@Test
	void givenBadSellerId_whenGetSeller_thenStatus404() {
		String badSellerId = "badSellerId";

		retrieveSellerMe(badSellerId).then().assertThat().statusCode(404);
	}

	@Test
	void givenSellerId_whenGetSeller_thenSellerDtoResponse() {
		SellerDtoResponse sellerDtoResponse = retrieveSellerMe(currentSellerId).as(SellerDtoResponse.class);

		Assertions.assertEquals(savedName, sellerDtoResponse.getName());
		Assertions.assertEquals(savedBio, sellerDtoResponse.getBio());
		Assertions.assertEquals(currentSellerId, sellerDtoResponse.getId());
		Assertions.assertEquals(0, sellerDtoResponse.getProducts().size());
	}

	@Test
	void givenSellerIdWithProductAndOffers_whenGetSeller_thenSellerWithProductAndOffers() throws JsonProcessingException {
		String productId = getProductIdByLocation(createProduct("title", "description", 12.5, Arrays.asList("SPORTS", "HOUSING") , currentSellerId));
		String otherProductId = getProductIdByLocation(createProduct("otherTitle", "otherDescription", 13.5, Arrays.asList("SPORTS", "HOUSING"), currentSellerId));
		createOfferForProduct(productId, "name", "asdb@asd.com", "18191234567", 48.23, "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.");
		createOfferForProduct(productId, "name", "asdb@asd.com", "18191234567", 48.23, "Donec porttitor interdum lacus sed finibus. Nam pulvinar facilisis posuere. Maecenas vel lorem amet.");
		SellerDtoResponse sellerDtoResponse = retrieveSellerMe(currentSellerId).as(SellerDtoResponse.class);

		Assertions.assertEquals(savedName, sellerDtoResponse.getName());
		Assertions.assertEquals(savedBio, sellerDtoResponse.getBio());
		Assertions.assertEquals(currentSellerId, sellerDtoResponse.getId());
		Assertions.assertEquals(2, sellerDtoResponse.getProducts().size());
	}

	public static Response retrieveSellerMe(String id) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.header("X-Seller-Id",id)
				.port(PORT)
				.basePath(subPathSellerById)
				.get();
	}
}
