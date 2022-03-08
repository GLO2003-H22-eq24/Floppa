package ulaval.glo2003.floppa.seller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SellerResourceCreateITTest extends ServerTestIT {

	private String anyName = "name";
	private String anyBio = "bio";
	private String validBirthdate = "2000-12-25";

	@Test
	void givenSellerInfo_whenSaveSeller_thenStatus201() throws JsonProcessingException {
		SaveSeller(anyName, anyBio, validBirthdate).then().assertThat().statusCode(201);
	}

	public static io.restassured.response.Response SaveSeller(String name, String bio, String birthDate) throws JsonProcessingException {
		Map<String, String> body = new HashMap<>();
		body.put("name", name);
		body.put("bio", bio);
		body.put("birthDate", birthDate);
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.port(PORT)
				.body(new ObjectMapper().writeValueAsString(body))
				.post("/sellers");
	}

	@Test
	void givenSellerInfoWithNullName_whenSaveSeller_thenStatus400() throws JsonProcessingException {
		SaveSeller(null, anyBio, validBirthdate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithNullBio_whenSaveSeller_thenStatus400() throws JsonProcessingException {
		SaveSeller(anyName, null, validBirthdate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithNullBirthDate_whenSaveSeller_thenStatus400() throws JsonProcessingException {
		String nullBirthDate = null;

		SaveSeller(anyName, anyBio, nullBirthDate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithBlankName_whenSaveSeller_thenStatus400() throws JsonProcessingException {
		String emptyName = "";

		SaveSeller(emptyName, anyBio, validBirthdate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithBlankBio_whenSaveSeller_thenStatus400() throws JsonProcessingException {
		String emptyBio = "";

		SaveSeller(anyName, emptyBio, validBirthdate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithNotDateBirthDate_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        String invalidBirthDate = "abcd";

		SaveSeller(anyName, anyBio, invalidBirthDate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfoWithNotFormattedAsYYYY_MM_DD_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        String invalidBirthDate = LocalDate.now().format(DateTimeFormatter.ISO_DATE);

		SaveSeller(anyName, anyBio, invalidBirthDate).then().assertThat().statusCode(400);
	}

	@Test
	void givenSellerInfo_whenSaveSeller_thenNotNullLocationId() throws JsonProcessingException {
		String sellerId = getSellerIdByLocation(SaveSeller(anyName, anyBio, validBirthdate));

		Assertions.assertNotNull(sellerId);
	}

	public static String getSellerIdByLocation(io.restassured.response.Response response) {
		String location = response.headers().getValue("location");
		return location.replace(RestAssured.baseURI + ":" + PORT + "/sellers/", "");
	}
}
