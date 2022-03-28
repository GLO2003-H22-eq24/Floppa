package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.message.ProductResponse;


import java.util.*;
import java.util.stream.Collectors;

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
	void givenNonExistingCategory_whenRetrieveProductWithFilter_thenNoProduct() throws JsonProcessingException {
		createProduct(savedTitle, savedDescription, savedPrice, List.of("BEAUTY"), savedSellerId);
		ProductResponse[] productResponse = retrieveProductWithFilter(null, null, List.of("ELECTRONICS"), null, null).as(ProductResponse[].class);

		Assertions.assertEquals(0,Arrays.stream(productResponse).count());
	}

	@Test
	void givenSavedCategory_whenRetrieveProductWithFilter_thenProductWithSavedCategory() throws JsonProcessingException {
		List<String> savedNewCategories = List.of("beauty");
		createProduct(savedTitle, savedDescription, savedPrice, savedNewCategories , savedSellerId);
		ProductResponse[] productResponse = retrieveProductWithFilter(null, null, savedNewCategories, null, null).as(ProductResponse[].class);

		for (ProductResponse product : productResponse) {
			Assertions.assertTrue(product.getCategories().contains("beauty"));
		}
	}

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenListProductDtoResponse() throws JsonProcessingException {
		ProductResponse[] productRespons = retrieveProductWithFilter(null, null, null, null, null).as(ProductResponse[].class);
		for (ProductResponse product: productRespons) {
			Assertions.assertEquals(savedTitle, product.getTitle());
			Assertions.assertEquals(savedDescription, product.getDescription());
			Assertions.assertEquals(savedPrice, product.getSuggestedPrice());
			Assertions.assertEquals(savedSellerId, product.getSeller().getId());
			Assertions.assertEquals(savedCategories, product.getCategories());
		}
	}

	@Test
	@Disabled //la db contient beaucoup plus que 1 product ayant ces conditions (à cause des autres tests)
	void givenFiltersSavedTitleWithOneProductWithSameTitle_whenRetrieveWithFilters_thenReturnOnlyOneProduct() throws JsonProcessingException {
		String otherTitle = "otherTile";
		createProduct(otherTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter(null, savedTitle, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(1, productRespons.length);
	}

	@Test
	void givenFiltersSellerIdWithNoProductWithSameTitle_whenRetrieveWithFilters_thenReturnOnlyOneProduct() throws JsonProcessingException {
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter("notSellerId", null, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(0, productRespons.length);
	}

	@Test
	@Disabled //la db contient beaucoup plus que 1 product ayant ces conditions (à cause des autres tests)
	void givenTwoProductsInProductList_whenNoFilter_thenReturnTwoProduct() throws JsonProcessingException {
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter(null, null, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(2, productRespons.length);
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
