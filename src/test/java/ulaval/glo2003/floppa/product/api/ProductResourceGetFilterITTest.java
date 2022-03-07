package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoRequest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceGetFilterITTest extends ServerTestIT {

	private List<String> savedCategories = List.of("SPORTS", "HOUSING");
	private String savedTitle = "title";
	private String savedDescription = "desc";
	private Double savedPrice = 12.2;
	private String productLocation;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		sellerId = getSellerIdByLocation(SaveSeller("test", "test", "2000-12-25"));
		productLocation = createProduct(savedTitle, savedDescription, savedPrice, savedCategories, sellerId).header("location");
	}

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		GetProductWithFilter(sellerId, anyTitle, knownCategories, 1.5, 5.0).then().assertThat().statusCode(200);
	}

	@Test
	void givenMissingTitle_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		List<String> categories = Arrays.asList("SPORTS", "HOUSING");
		String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
		createProduct("test", "desc", 2.0, categories, sellerId);
		var response = GetProductWithFilter(sellerId, null, categories, 1.5, 5.0);
		response.then().assertThat().statusCode(200);
	}

	@Test
	void givenMissingSellerId_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String title = "test";
		List<String> categories = Arrays.asList("SPORTS", "HOUSING");
		String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
		createProduct(title, "desc", 2.0, categories, sellerId);
		var response = GetProductWithFilter(null, title, categories, 1.5, 5.0);
		response.then().assertThat().statusCode(200);
	}

	@Test
	void givenMissingMinPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String title = "test";
		List<String> categories = Arrays.asList("SPORTS", "HOUSING");
		String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
		createProduct(title, "desc", 2.0, categories, sellerId);
		var response = GetProductWithFilter(sellerId, title, categories, null, 5.0);
		response.then().assertThat().statusCode(200);
	}

	@Test
	void givenMissingMaxPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String title = "test";
		List<String> categories = Arrays.asList("SPORTS", "HOUSING");
		String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
		createProduct(title, "desc", 2.0, categories, sellerId);
		var response = GetProductWithFilter(sellerId, title, categories, 1.5, null);
		response.then().assertThat().statusCode(200);
	}

	@Test
	void givenMissingCategories_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String title = "test";
		List<String> categories = Arrays.asList("SPORTS", "HOUSING");
		String sellerId = getSellerId(SaveSeller("test", "test", "2000-12-25"));
		createProduct(title, "desc", 2.0, categories, sellerId);
		var response = GetProductWithFilter(sellerId, title, null, 1.5, 5.0);
		response.then().assertThat().statusCode(200);
	}

	public static io.restassured.response.Response GetProductWithFilter(String sellerId, String title, List<String> categories, Double minPrice, Double maxPrice) throws JsonProcessingException {
		Map<String, Object> queryParams = new HashMap<>();
		queryParams.put("sellerId", sellerId);
		queryParams.put("title", title);
		queryParams.put("categories", categories);
		queryParams.put("minPrice", minPrice);
		queryParams.put("maxPrice", maxPrice);
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.queryParams(queryParams)
				.port(PORT)
				.get("/products");
	}
}
