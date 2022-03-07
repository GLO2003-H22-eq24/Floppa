package ulaval.glo2003.floppa.seller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

public class SellerResourceGetITTest extends ServerTestIT {

	private String currentSellerId;
	private String anyName = "name";
	private String anyBio = "bio";
	private String validBirthDate = "2000-12-25";
	private static String subPathSellerById = "/sellers/{id}";

	@BeforeEach
	void givenSeller() throws JsonProcessingException {
		currentSellerId = SellerResourceCreateITTest.getSellerIdByLocation(SellerResourceCreateITTest.SaveSeller(anyName, anyBio, validBirthDate));
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
		SellerDtoResponse sellerDtoResponse = retrieveSeller(currentSellerId).as(SellerDtoResponse.class);

		Assertions.assertEquals(anyName, sellerDtoResponse.getName());
		Assertions.assertEquals(anyBio, sellerDtoResponse.getBio());
		Assertions.assertEquals(currentSellerId, sellerDtoResponse.getId());
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
