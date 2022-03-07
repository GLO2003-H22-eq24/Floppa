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

	private String currentId;
	private String anyName = "name";
	private String anyBio = "bio";
	private String validBirthDate = "2000-12-25";
	private static String subPathSellerById = "/sellers/{id}";

	@BeforeEach
	void givenSeller() throws JsonProcessingException {
		currentId = SellerResourceCreateITTest.getSellerIdByLocation(SellerResourceCreateITTest.SaveSeller(anyName, anyBio, validBirthDate));
	}

	@Test
	void givenSellerLocation_whenGetSeller_thenStatus200() {
		retrieveSeller(currentId).then().assertThat().statusCode(200);
	}

	@Test
	void givenBadSellerLocation_whenGetSeller_thenStatus400() {
		String badSellerId = "badSellerId";

		retrieveSeller(badSellerId).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerLocation_whenGetSeller_thenSellerDtoResponse() {
		SellerDtoResponse sellerDtoResponse = retrieveSeller(currentId).as(SellerDtoResponse.class);

		//TODO: check si tout les champs sont good
		Assertions.assertEquals(anyName, sellerDtoResponse.getName());
		Assertions.assertEquals(anyBio, sellerDtoResponse.getBio());
		Assertions.assertEquals(currentId, sellerDtoResponse.getId());
	}

	public static Response retrieveSeller(String id) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.port(PORT)
				.basePath(subPathSellerById)
				.pathParam("id", id)
				.get(id);
	}
}
