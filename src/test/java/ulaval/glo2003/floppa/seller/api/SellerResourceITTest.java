package ulaval.glo2003.floppa.seller.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class SellerResourceITTest extends ServerTestIT {
    @Test
    void whenSaveSeller_withGoodParam_thenStatus201() throws JsonProcessingException {
        SaveSeller("name", "bio", "2000-12-25").then().assertThat().statusCode(201);
    }

    @Test
    void whenSaveSeller_withMissingName_thenStatus400() throws JsonProcessingException {
        SaveSeller(null, "bio", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withMissingBio_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", null, "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withMissingDate_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", null).then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withBlankName_thenStatus400() throws JsonProcessingException {
        SaveSeller("", "bio", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withBlankBio_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "", "2000-12-25").then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withInvalidDateFormat_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", "abcd").then().assertThat().statusCode(400);
    }

    @Test
    void whenSaveSeller_withYoungerThan18_thenStatus400() throws JsonProcessingException {
        SaveSeller("name", "bio", LocalDate.now().format(DateTimeFormatter.ISO_DATE)).then().assertThat().statusCode(400);
    }

    public static io.restassured.response.Response SaveSeller(String name, String bio, String birthDate) throws JsonProcessingException {
        Map<String, String> body = new HashMap<>();
        if (name != null) {
            body.put("name", name);
        }
        if (bio != null) {
            body.put("bio", bio);
        }
        if (birthDate != null) {
            body.put("birthDate", birthDate);
        }

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .port(PORT)
                .body(new ObjectMapper().writeValueAsString(body))
                .post("/sellers");
    }
}
