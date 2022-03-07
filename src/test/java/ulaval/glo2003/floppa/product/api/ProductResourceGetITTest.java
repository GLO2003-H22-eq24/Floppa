package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.offers.api.OffersDto;
import ulaval.glo2003.floppa.product.api.message.ProductDtoResponse;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;

import java.time.LocalTime;
import java.util.List;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceGetITTest extends ServerTestIT {

	//Todo ajouter attribut classe ProductDto
	private String id;
	private String productLocation;
	private String title;
	private String description;
	private Double suggestedPrice;
	private OffersDto offers;
	private LocalTime createdAt;
	private SellerDtoResponse seller;
	private List<String> categories = List.of("SPORTS","ELECTRONICS","APPAREL","BEAUTY","HOUSING","OTHER");

	private String savedTitle = "title";
	private String savedDescription = "desc";
	private double savedSuggestedPrice = 2.0;
	private String validBirthDate = "2000-12-25";
	private static String subPathSellerById = "/sellers/{id}";



	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		String sellerId = getSellerIdByLocation(SaveSeller(savedTitle, savedDescription, validBirthDate));
		productLocation = createProduct(savedTitle, savedDescription, savedSuggestedPrice, categories, sellerId).header("location");
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
		ProductDtoResponse productDtoResponse = retrieveProduct(productLocation).as(ProductDtoResponse.class);

		productDtoResponse.getCategories();
		Assertions.assertEquals(savedTitle, productDtoResponse.getTitle());
		Assertions.assertEquals(savedDescription, productDtoResponse.getDescription());
		Assertions.assertEquals(savedSuggestedPrice, productDtoResponse.getSuggestedPrice());
		Assertions.assertEquals(categories, productDtoResponse.getCategories());



	}


	public static io.restassured.response.Response retrieveProduct(String location) throws JsonProcessingException {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.get(location);
	}
}
