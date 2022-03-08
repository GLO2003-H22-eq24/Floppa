package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

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
		retrieveProduct(productLocation + "aa")
				.then()
				.assertThat().statusCode(404);
	}

	@Test
	void givenProduct_whenRetrieveProduct_thenProductDtoResponse() throws JsonProcessingException {
		ProductDtoResponse productDtoResponse =retrieveProduct(productLocation).as(ProductDtoResponse.class);

		Assertions.assertEquals(savedTitle, productDtoResponse.getTitle());
		Assertions.assertEquals(savedDescription, productDtoResponse.getDescription());
		Assertions.assertEquals(savedSuggestedPrice, productDtoResponse.getSuggestedPrice());
		Assertions.assertEquals(savedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()), productDtoResponse.getCategories());
		Assertions.assertEquals(savedSellerId, productDtoResponse.getSeller().getId());
		Assertions.assertEquals(0, productDtoResponse.getOffers().getCount());
	}

	@Test
	void givenProduct_whenRetrieveSeller_thenListProductDtoResponse() throws JsonProcessingException {
		List<ProductDtoResponse> productDtoResponses = retrieveSeller(savedSellerId).as(SellerDtoResponse.class).getProducts();

		ProductDtoResponse productDtoResponse = productDtoResponses.stream().findFirst().orElse(new ProductDtoResponse());
		Assertions.assertEquals(savedTitle, productDtoResponse.getTitle());
		Assertions.assertEquals(savedDescription, productDtoResponse.getDescription());
		Assertions.assertEquals(savedSuggestedPrice, productDtoResponse.getSuggestedPrice());
		Assertions.assertEquals(savedCategories.stream().map(String::toLowerCase).collect(Collectors.toList()), productDtoResponse.getCategories());
		Assertions.assertEquals(0, productDtoResponse.getOffers().getCount());
	}

	@Test
	void givenManyProducts_whenRetrieveSeller_thenAsManyProductIsRetrieved() throws JsonProcessingException {
		createProduct(savedTitle, savedDescription, savedSuggestedPrice, savedCategories, savedSellerId);

		List<ProductDtoResponse> productDtoResponses = retrieveSeller(savedSellerId).as(SellerDtoResponse.class).getProducts();

		Assertions.assertEquals(2, productDtoResponses.size());
	}


	public static io.restassured.response.Response retrieveProduct(String location) throws JsonProcessingException {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.get(location);
	}
}
