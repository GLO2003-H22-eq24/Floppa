package ulaval.glo2003.integration.health.story;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import ulaval.glo2003.integration.config.ServerTest;
import ulaval.glo2003.integration.health.steps.HealthCheckStep;

public class HealthOk extends ServerTest {
	@Test
	void whenCheckHealth_thenCode200() {
		Response response = HealthCheckStep.check();

		response.then().assertThat().statusCode(200);
	}
}
