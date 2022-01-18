package ulaval.glo2003.integration.health.steps;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static ulaval.glo2003.integration.config.RestAssuredConfig.PORT;

public class HealthCheckStep {
	private static final String PATH = "/health";
	public static Response check() {
		return RestAssured.given().header("Content-Type", "application/json").port(PORT).basePath(PATH).get();
	}
}
