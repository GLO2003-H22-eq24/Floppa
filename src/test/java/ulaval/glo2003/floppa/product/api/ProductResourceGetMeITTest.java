package ulaval.glo2003.floppa.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.product.api.response.ProductViewResponse;

import java.util.List;

import static ulaval.glo2003.floppa.product.api.ProductResourceCreateITTest.createProduct;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.SaveSeller;
import static ulaval.glo2003.floppa.seller.api.SellerResourceCreateITTest.getSellerIdByLocation;

public class ProductResourceGetMeITTest extends ServerTestIT {
	private List<String> savedCategories = List.of("SPORTS", "ELECTRONICS", "APPAREL", "BEAUTY", "HOUSING", "OTHER");
	private String savedTitle = "title";
	private String savedDescription = "desc";
	private double savedSuggestedPrice = 2.0;
	private String validBirthDate = "2000-12-25";
	private String savedSellerId;

	@BeforeEach
	void givenProduct() throws JsonProcessingException {
		savedSellerId = getSellerIdByLocation(SaveSeller(savedTitle, savedDescription, validBirthDate));
		createProduct(savedTitle, savedDescription, savedSuggestedPrice, savedCategories, savedSellerId);
		createProduct(savedTitle, savedDescription, savedSuggestedPrice, savedCategories, savedSellerId);
	}

	@Test
	void givenSellerId_whenRetrieveMeProduct_thenProductsWithViews() {
		ProductViewResponse[] productViewResponses = retrieveMeProduct(savedSellerId).as(ProductViewResponse[].class);

		Assertions.assertEquals(2, productViewResponses.length);
	}

	public static io.restassured.response.Response retrieveMeProduct(String sellerId) {
		return RestAssured.given()
				.header("Content-Type", "application/json")
				.header("X-Seller-Id", sellerId)
				.port(PORT)
				.basePath("/products/@me/")
				.get();
	}
}
