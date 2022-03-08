package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;


import java.util.*;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceGetFilterITTest extends ServerTestIT {

	private List<String> savedCategories = List.of("sports", "housing");
	private String savedTitle = "title";
	private String savedDescription = "desc";
	private Double savedPrice = 12.2;
	private String savedSellerId;
	private String savedName = "test";
	private String savedBio = "test";
	private String savedBirthDate = "2000-12-25";


	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);
	}

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingTitle_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(savedSellerId, null, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingSellerId_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(null, savedTitle, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingCategories_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(savedSellerId, savedTitle, null, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingMinPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, null, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingMaxPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, savedPrice - 1, null).then().assertThat().statusCode(200);
	}

	@Test
	void givenNoFilters_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		retrieveProductWithFilter(null, null, null, null, null).then().assertThat().statusCode(200);
	}

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenListProductDtoResponse() throws JsonProcessingException {
		ProductDtoResponse[] productDtoResponses = retrieveProductWithFilter(null, null, null, null, null).as(ProductDtoResponse[].class);
		for (ProductDtoResponse product: productDtoResponses) {
			Assertions.assertEquals(savedTitle, product.getTitle());
			Assertions.assertEquals(savedDescription, product.getDescription());
			Assertions.assertEquals(savedPrice, product.getSuggestedPrice());
			Assertions.assertEquals(savedSellerId, product.getSeller().getId());
			Assertions.assertEquals(savedCategories, product.getCategories());
		}
	}


	public static io.restassured.response.Response retrieveProductWithFilter(String sellerId, String title, List<String> categories, Double minPrice, Double maxPrice) throws JsonProcessingException {
		Map<String, Object> queryParams = new HashMap<>();
		Optional.ofNullable(sellerId).ifPresent(val-> queryParams.put("sellerId", sellerId));
		Optional.ofNullable(title).ifPresent(val -> queryParams.put("title", title));
		Optional.ofNullable(categories).ifPresent(val -> queryParams.put("categories", categories));
		Optional.ofNullable(minPrice).ifPresent(val -> queryParams.put("minPrice", minPrice));
		Optional.ofNullable(maxPrice).ifPresent(val -> queryParams.put("maxPrice", maxPrice));
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.queryParams(queryParams)
				.port(PORT)
				.get("/products");
	}
}
