package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;
import java.util.List;
import java.util.stream.Collectors;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;
import static ulaval.glo2003.floppa.seller.api.SellerResourceGetITTest.retrieveSeller;

public class ProductResourceGetITTest extends ServerTestIT {
	private String productLocation;
	private List<String> savedCategories = List.of("SPORTS","ELECTRONICS","APPAREL","BEAUTY","HOUSING","OTHER");
	private String savedTitle = "title";
	private String savedDescription = "desc";
	private double savedSuggestedPrice = 2.0;
	private String validBirthDate = "2000-12-25";
	private String savedSellerId;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		savedSellerId = getSellerIdByLocation(SaveSeller(savedTitle, savedDescription, validBirthDate));
		productLocation = createProduct(savedTitle, savedDescription, savedSuggestedPrice, savedCategories, savedSellerId).header("location");
	}

	@Test
	void givenProduct_whenRetrieveProduct_thenStatus200() throws JsonProcessingException {
		retrieveProduct(productLocation)
				.then()
				.assertThat().statusCode(200);
	}

	@Test
	void givenInvalidProduct_whenRetrieveProduct_thenStatus404() throws JsonProcessingException {
		String invalidTag = "aa";
		retrieveProduct(productLocation + invalidTag)
				.then()
				.assertThat().statusCode(404);
	}

	@Test
	void givenProduct_whenRetrieveProduct_thenProductDtoResponse() throws JsonProcessingException {
		ProductResponse productResponse =retrieveProduct(productLocation).as(ProductResponse.class);

		Assertions.assertEquals(savedTitle, productResponse.getTitle());
		Assertions.assertEquals(savedDescription, productResponse.getDescription());
		Assertions.assertEquals(savedSuggestedPrice, productResponse.getSuggestedPrice());
		Assertions.assertEquals(savedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()), productResponse.getCategories());
		Assertions.assertEquals(savedSellerId, productResponse.getSeller().getId());
		Assertions.assertEquals(0, productResponse.getOffers().getCount());
	}

	@Test
	void givenProduct_whenRetrieveSeller_thenListProductDtoResponse() throws JsonProcessingException {
		List<ProductResponse> productRespons = retrieveSeller(savedSellerId).as(SellerResponse.class).getProducts();

		ProductResponse productResponse = productRespons.stream().findFirst().orElse(new ProductResponse());
		Assertions.assertEquals(savedTitle, productResponse.getTitle());
		Assertions.assertEquals(savedDescription, productResponse.getDescription());
		Assertions.assertEquals(savedSuggestedPrice, productResponse.getSuggestedPrice());
		Assertions.assertEquals(savedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()), productResponse.getCategories());
		Assertions.assertEquals(0, productResponse.getOffers().getCount());
	}

	@Test
	void givenManyProducts_whenRetrieveSeller_thenAsManyProductIsRetrieved() throws JsonProcessingException {
		createProduct(savedTitle, savedDescription, savedSuggestedPrice, savedCategories, savedSellerId);

		List<ProductResponse> productResponse = retrieveSeller(savedSellerId).as(SellerResponse.class).getProducts();

		Assertions.assertEquals(2, productResponse.size());
	}


	public static io.restassured.response.Response retrieveProduct(String location) throws JsonProcessingException {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.get(location);
	}
}
