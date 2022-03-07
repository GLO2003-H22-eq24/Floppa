package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

import java.util.*;

public class ProductResourceITTest extends ServerTestIT {

    @Nested
    public class CreateProduct {
        @Test
        void whenCreateProduct_withGoodParam_thenStatus201() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("title", "desc", 2.0, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(201);
        }

        @Test
        void whenCreateProduct_withMissingTitle_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(null, "desc", 2.0, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withBlankTitle_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("", "desc", 2.0, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withMissingDescription_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", null, 2.0, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withBlankDescription_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "", 2.0, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withMissingPrice_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "testtest", null, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withPriceTooSmall_thenStatus400() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "testtest", 0.5, null, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }

        @Test
        void whenCreateProduct_withGoodCategories_thenStatus201() throws JsonProcessingException {
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "testtest", 2.0, categories, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(201);
        }

        @Test
        void whenCreateProduct_withBadCategories_thenStatus400() throws JsonProcessingException {
            List<String> categories = Arrays.asList("abcvd", "efgh");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "testtest", 2.0, categories, sellerId)
                    .then()
                    .assertThat()
                    .statusCode(400);
        }
    }

    @Nested
    public class RetrieveProduct {
        @Test
        void whenRetrieveProduct_withGoodLocation_thenStatus200() throws JsonProcessingException {
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            String location = createProduct("title", "desc", 2.0, null, sellerId).header("location");
            GetProduct(location).then().assertThat().statusCode(200);
        }
    }

    @Nested
    public class RetrieveProductWithFilter {
        @Test
        void whenRetrieveProductWithFilter_withGoodFilters_thenStatus200() throws JsonProcessingException {
            String title = "test";
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(title, "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(sellerId, title, categories, 1.5, 5.0);
            var responseBody = response.body().as(Map.class);

            assert responseBody.containsKey("id");
            assert responseBody.containsKey("createdAt");
            assert responseBody.containsKey("title");
            assert responseBody.containsKey("description");
            assert responseBody.containsKey("suggestedPrice");
            assert responseBody.containsKey("categories");
            assert responseBody.containsKey("seller");
            assert responseBody.containsKey("offers");

            response.then().assertThat().statusCode(200);
        }

        @Test
        void whenRetrieveProductWithFilter_withMissingTitle_thenStatus200() throws JsonProcessingException {
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct("test", "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(sellerId, null, categories, 1.5, 5.0);
            response.then().assertThat().statusCode(200);
        }

        @Test
        void whenRetrieveProductWithFilter_withMissingSellerId_thenStatus200() throws JsonProcessingException {
            String title = "test";
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(title, "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(null, title, categories, 1.5, 5.0);
            response.then().assertThat().statusCode(200);
        }

        @Test
        void whenRetrieveProductWithFilter_withMissingMinPrice_thenStatus200() throws JsonProcessingException {
            String title = "test";
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(title, "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(sellerId, title, categories, null, 5.0);
            response.then().assertThat().statusCode(200);
        }

        @Test
        void whenRetrieveProductWithFilter_withMissingMaxPrice_thenStatus200() throws JsonProcessingException {
            String title = "test";
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(title, "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(sellerId, title, categories, 1.5, null);
            response.then().assertThat().statusCode(200);
        }

        @Test
        void whenRetrieveProductWithFilter_withMissingCategories_thenStatus200() throws JsonProcessingException {
            String title = "test";
            List<String> categories = Arrays.asList("SPORTS", "HOUSING");
            String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
            createProduct(title, "desc", 2.0, categories, sellerId);
            var response = GetProductWithFilter(sellerId, title, null, 1.5, 5.0);
            response.then().assertThat().statusCode(200);
        }
    }

    public static io.restassured.response.Response createProduct(String title, String description, Double suggestedPrice, List<String> categories, String sellerId) throws JsonProcessingException {
        Map<String, Object> body = new HashMap<>();
        if (title != null) {
            body.put("title", title);
        }
        if (description != null) {
            body.put("description", description);
        }
        if (suggestedPrice != null) {
            body.put("suggestedPrice", suggestedPrice);
        }
        if (categories != null) {
            body.put("categories", categories);
        }


        var test = RestAssured.given()
                .header("Content-Type", "application/json")
                .header("X-Seller-Id", sellerId)
                .port(PORT)
                .body(new ObjectMapper().writeValueAsString(body));


        return test.post("/products");
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

    public static io.restassured.response.Response GetProduct(String location) throws JsonProcessingException {

        return RestAssured.given()
                .header("Content-Type", "application/json")
                .get(location);
    }

    public static io.restassured.response.Response GetProductWithFilter(String sellerId, String title, List<String> categories, Double minPrice, Double maxPrice) throws JsonProcessingException {
        Map<String, Object> queryParams = new HashMap<>();
        if (sellerId != null){
            queryParams.put("sellerId", sellerId);
        }
        if (title != null){
            queryParams.put("title", title);
        }
        if (categories != null){
            queryParams.put("categories", categories);
        }
        if (minPrice != null){
            queryParams.put("minPrice", minPrice);
        }
        if (maxPrice != null){
            queryParams.put("maxPrice", maxPrice);
        }


        return RestAssured.given()
                .header("Content-Type", "application/json")
                .queryParams(queryParams)
                .port(PORT)
                .get("/products");
    }

    public static String getSellerId(io.restassured.response.Response response) {
        String location = response.headers().getValue("location");
        return location.replace(RestAssured.baseURI + ":" + PORT + "/sellers/", "");
    }
}
