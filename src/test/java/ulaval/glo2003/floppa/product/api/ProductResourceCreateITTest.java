package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

import java.util.*;

import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceCreateITTest extends ServerTestIT {

    private static String titleField = "title";;
    private static String descriptionField = "description";
    private static String suggestedPriceField = "suggestedPrice";
    private static String categoriesField = "categories";
    private static String headerSellerId = "X-Seller-Id";
    private static String subPathProduct = "/products";

    private String sellerId;

    @BeforeEach
    void givenSellerId() throws JsonProcessingException {
        sellerId = getSellerIdByLocation(SaveSeller("test", "test", "2000-12-25"));
    }

    @Test
    void givenGoodParam_whenCreateProduct_thenStatus201() throws JsonProcessingException {
        createProduct("title", "desc", 2.0, null, sellerId)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void givenMissingTitle_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        createProduct(null, "desc", 2.0, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenBlankTitle_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        createProduct("", "desc", 2.0, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenMissingDescription_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        createProduct("test", null, 2.0, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenBlankDescription_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        createProduct("test", "", 2.0, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void whenCreateProduct_withMissingPrice_thenStatus400() throws JsonProcessingException {
        createProduct("test", "test", null, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenPriceTooSmall_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        createProduct("test", "test", 0.5, null, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenKnownCategories_whenCreateProduct_thenStatus201() throws JsonProcessingException {
        List<String> categories = Arrays.asList("SPORTS", "HOUSING");

        createProduct("test", "test", 2.0, categories, sellerId)
                .then()
                .assertThat()
                .statusCode(201);
    }

    @Test
    void givenBadCategories_whenCreateProduct_thenStatus400() throws JsonProcessingException {
        List<String> categories = Arrays.asList("abcvd", "efgh");

        createProduct("test", "test", 2.0, categories, sellerId)
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    void givenSellerAndValidCategories_whenCreateProduct_thenProductIdFromLocation() throws JsonProcessingException {
        List<String> categories = Arrays.asList("SPORTS", "HOUSING");

        String productIdByLocation = getProductIdByLocation(createProduct("test", "test", 2.0, categories, sellerId));

        Assertions.assertNotNull(productIdByLocation);
    }
    public static String getProductIdByLocation(io.restassured.response.Response response) {
        String location = response.headers().getValue("location");
        return location.replace(RestAssured.baseURI + ":" + PORT + "/products/", "");
    }

    public static io.restassured.response.Response createProduct(String title, String description, Double suggestedPrice, List<String> categories, String sellerId) throws JsonProcessingException {
        Map<String, Object> body = new HashMap<>();
        body.put(titleField, title);
        body.put(descriptionField, description);
        body.put(suggestedPriceField, suggestedPrice);
        body.put(categoriesField, categories);
        return RestAssured.given()
                .header("Content-Type", "application/json")
                .header(headerSellerId, sellerId)
                .port(PORT)
                .body(new ObjectMapper().writeValueAsString(body)).post(subPathProduct);
    }
}
