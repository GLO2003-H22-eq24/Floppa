package ulaval.glo2003.floppa.seller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;

public class SellerResourceGetITTest extends ServerTestIT {

	private String currentSellerId;
	private String savedName = "name";
	private String savedBio = "bio";
	private String validBirthDate = "2000-12-25";
	private static String subPathSellerById = "/sellers/{id}";

	@BeforeEach
	void givenSeller() throws JsonProcessingException {
		currentSellerId = SellerResourceCreateITTest.getSellerIdByLocation(SellerResourceCreateITTest.SaveSeller(savedName, savedBio, validBirthDate));
	}

	@Test
	void givenSellerId_whenGetSeller_thenStatus200() {
		retrieveSeller(currentSellerId).then().assertThat().statusCode(200);
	}

	@Test
	void givenBadSellerId_whenGetSeller_thenStatus404() {
		String badSellerId = "badSellerId";

		retrieveSeller(badSellerId).then().assertThat().statusCode(404);
	}

	@Test
	void givenSellerId_whenGetSeller_thenSellerDtoResponse() {
		SellerResponse sellerResponse = retrieveSeller(currentSellerId).as(SellerResponse.class);

		Assertions.assertEquals(savedName, sellerResponse.getName());
		Assertions.assertEquals(savedBio, sellerResponse.getBio());
		Assertions.assertEquals(currentSellerId, sellerResponse.getId());
		Assertions.assertEquals(0, sellerResponse.getProducts().size());
	}

	public static Response retrieveSeller(String id) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.port(PORT)
				.basePath(subPathSellerById)
				.pathParam("id", id)
				.get();
	}
}
