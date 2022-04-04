package ulaval.glo2003.floppa.health.api;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.ServerTestIT;
import ulaval.glo2003.floppa.health.api.message.HealthResponse;

class HealthResourceITTest extends ServerTestIT {

	@Test
	void whenCheckHealth_thenStatus200() {
		checkHealth().then().assertThat().statusCode(200);
	}

	@Test
	void whenCheckHealth_thenHealthResponse() {
		HealthResponse healthResponse = checkHealth().as(HealthResponse.class);
		Assertions.assertTrue(healthResponse.isApi());
	}

	public static io.restassured.response.Response checkHealth(){
		return RestAssured.given().header("Content-Type", "application/json").port(PORT).basePath("/health").get();
	}
}