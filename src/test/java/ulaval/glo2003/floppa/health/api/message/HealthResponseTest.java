package ulaval.glo2003.floppa.health.api.message;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class HealthResponseTest {
    final boolean starting_db = true;
    final boolean starting_api = true;
    HealthResponse healthResponse = new HealthResponse(starting_db, starting_api);

    @Test
    void givenHealthResponse_whenIsDb_thenGetIsDbValid() {
        boolean isItDb = healthResponse.isDb();
        assertEquals(starting_db, isItDb);
    }
    @Test
    void givenHealthResponse_whenIsApi_thenGetIsApiValid() {
        boolean isItApi = healthResponse.isApi();
        assertEquals(starting_api, isItApi);
    }
    @Test
    void givenValidb_whenSetdb_thenSetDbValid() {
        boolean new_db = false;

        healthResponse.setDb(new_db);

        assertEquals(new_db, healthResponse.isDb());
    }
    @Test
    void givenValidApi_whenSetApi_thenSetApiValid() {
        boolean new_api = false;

        healthResponse.setApi(new_api);

        assertEquals(new_api, healthResponse.isApi());
    }

}
