package ulaval.glo2003.floppa.health.api;

import org.junit.jupiter.api.Test;
import ulaval.glo2003.floppa.health.api.message.HealthResponse;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealthAssemblerTest {

    HealthAssembler healthAssembler = new HealthAssembler();
    boolean db = true;

    @Test
    void givenHealthAssembler_whenDbCheckIsTrue_thenHealthResponseCreatedWithIsDbTrue() {
        HealthResponse healthResponse = healthAssembler.toResponse(db);

        assertEquals(db, healthResponse.isDb());
    }

}
