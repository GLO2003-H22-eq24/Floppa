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
    private String validBirthdate ="2000-12-25";

    @Test
    void givenSellerInfo_whenSaveSeller_thenStatus201() throws JsonProcessingException {
        SaveSeller(anyName, "bio", validBirthdate).then().assertThat().statusCode(201);
    }

    @Test
    void givenSellerInfoWithNullName_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller(null, "bio", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithNullBio_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", null, "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithNullBirthDate_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", null).then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithBlankName_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("", "bio", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithBlankBio_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithNotDateBirthDate_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", "abcd").then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfoWithNotFormattedAsYYYY_MM_DD_whenSaveSeller_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", LocalDate.now().format(DateTimeFormatter.ISO_DATE)).then().assertThat().statusCode(400);
    }

    @Test
    void givenSellerInfo_whenSaveSeller_thenNotNullLocationId() throws JsonProcessingException {
        String sellerId = getSellerIdByLocation(SaveSeller("name", "bio", "2000-12-25"));

        Assertions.assertNotNull(sellerId);
    }

    public static String getSellerIdByLocation(io.restassured.response.Response response) {
        String location = response.headers().getValue("location");
        return location.replace(RestAssured.baseURI + ":" + PORT + "/sellers/", "");
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
}
