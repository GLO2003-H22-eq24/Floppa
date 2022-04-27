package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;


import java.util.*;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceGetFilterITTest extends ServerTestIT {

	private List<String> savedCategories = List.of("sports", "APPAREL");
	private String savedTitle = "title";
	private String savedDescription = "desc";
	private Double savedPrice = 12.2;
	private String savedName = "test";
	private String savedBio = "test";
	private String savedBirthDate = "2000-12-25";

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingTitle_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(savedSellerId, null, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingSellerId_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(null, savedTitle, savedCategories, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingCategories_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(savedSellerId, savedTitle, null, savedPrice - 1, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingMinPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, null, savedPrice + 1).then().assertThat().statusCode(200);
	}

	@Test
	void givenFiltersWithMissingMaxPrice_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(savedSellerId, savedTitle, savedCategories, savedPrice - 1, null).then().assertThat().statusCode(200);
	}

	@Test
	void givenNoFilters_whenRetrieveProductWithFilter_thenStatus200() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		retrieveProductWithFilter(null, null, null, null, null).then().assertThat().statusCode(200);
	}

	@Test
	void givenNonExistingCategory_whenRetrieveProductWithFilter_thenNoProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		createProduct(savedTitle, savedDescription, savedPrice, List.of("BEAUTY"), savedSellerId);
		ProductResponse[] productResponse = retrieveProductWithFilter(savedSellerId, null, List.of("ELECTRONICS"), null, null).as(ProductResponse[].class);

		Assertions.assertEquals(0,Arrays.stream(productResponse).count());
	}

	@Test
	void givenSavedCategory_whenRetrieveProductWithFilter_thenProductWithSavedCategory() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		List<String> savedNewCategories = List.of("beauty");
		createProduct(savedTitle, savedDescription, savedPrice, savedNewCategories , savedSellerId);
		ProductResponse[] productResponse = retrieveProductWithFilter(null, null, savedNewCategories, null, null).as(ProductResponse[].class);

		for (ProductResponse product : productResponse) {
			Assertions.assertTrue(product.getCategories().contains("beauty"));
		}
	}

	@Test
	void givenFilters_whenRetrieveProductWithFilter_thenListProductDtoResponse() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter(null, null, null, null, null).as(ProductResponse[].class);

		Assertions.assertTrue(productRespons.length >= 1);
	}

	@Test
	void givenFiltersSavedTitleWithOneProductWithSameTitle_whenRetrieveWithFilters_thenReturnOnlyOneProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);
		String otherTitle = "superSpecialTitle";
		createProduct(otherTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter(null, otherTitle, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(1, productRespons.length);
	}

	@Test
	void givenFiltersSellerIdWithNoProductWithSameTitle_whenRetrieveWithFilters_thenReturnOnlyOneProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter("notSellerId", null, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(0, productRespons.length);
	}

	@Test
	void givenTwoProductsInProductList_whenFilterById_thenReturnTwoProduct() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);
		createProduct(savedTitle, savedDescription, savedPrice, savedCategories, savedSellerId);

		ProductResponse[] productRespons = retrieveProductWithFilter(savedSellerId, null, null, null, null).as(ProductResponse[].class);

		Assertions.assertEquals(2, productRespons.length);
	}

	@Test
	void givenProductsWithCategories_whenFilterByCategory_thenOnlyThoseProducts() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, List.of("sports", "APPAREL"), savedSellerId);
		createProduct(savedTitle, savedDescription, savedPrice, List.of("HOUSING", "APPAREL"), savedSellerId);
		createProduct(savedTitle, savedDescription, savedPrice, List.of("sports"), savedSellerId);
		createProduct(savedTitle, savedDescription, savedPrice, List.of("HOUSING"), savedSellerId);

		ProductResponse[] productResponse = retrieveProductWithFilter(savedSellerId, savedTitle, List.of("HOUSING", "APPAREL"), null, null).as(ProductResponse[].class);

		Assertions.assertEquals(3, Arrays.stream(productResponse).count());
	}

	@Test
	void givenMinPrice_whenFilterByMinPrice_then1Product() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, List.of("sports", "APPAREL"), savedSellerId);
		createProduct(savedTitle, savedDescription, 100., List.of("HOUSING", "APPAREL"), savedSellerId);

		ProductResponse[] productResponse = retrieveProductWithFilter(savedSellerId, null, null, 99., null).as(ProductResponse[].class);

		Assertions.assertEquals(1, Arrays.stream(productResponse).count());
	}

	@Test
	void givenMaxPrice_whenFilterByMinPrice_then2Product() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, List.of("sports", "APPAREL"), savedSellerId);
		createProduct(savedTitle, savedDescription, 100., List.of("HOUSING", "APPAREL"), savedSellerId);

		ProductResponse[] productResponse = retrieveProductWithFilter(savedSellerId, null, null, null, 105.).as(ProductResponse[].class);

		Assertions.assertEquals(2, Arrays.stream(productResponse).count());
	}

	@Test
	void givenPriceInRange_whenFilterByMinPrice_then1Product() throws JsonProcessingException {
		String savedSellerId = getSellerIdByLocation(SaveSeller(savedName, savedBio, savedBirthDate));
		createProduct(savedTitle, savedDescription, savedPrice, List.of("sports", "APPAREL"), savedSellerId);
		createProduct(savedTitle, savedDescription, 100., List.of("HOUSING", "APPAREL"), savedSellerId);

		ProductResponse[] productResponse = retrieveProductWithFilter(savedSellerId, null, null, 99., 105.).as(ProductResponse[].class);

		Assertions.assertEquals(1, Arrays.stream(productResponse).count());
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
