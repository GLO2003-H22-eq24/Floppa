package ulaval.glo2003.floppa.healt.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;

class HealthResourceTestIT extends ServerTestIT {

	@Test
	void whenCheckHealth_thenStatus200() {
		checkHealth().then().assertThat().statusCode(200);
	}
	public static io.restassured.response.Response checkHealth(){
		return RestAssured.given().header("Content-Type", "application/json").port(PORT).basePath("/health").get();
	}
}