package ulaval.glo2003.floppa.app.domain;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ulaval.glo2003.floppa.app.domain.ErrorCode.MISSING_PARAMETER;

public class ErrorExceptionTest {

    ErrorException errorException = new ErrorException(MISSING_PARAMETER);
    String actualErrorExcpetionName = "MISSING_PARAMETER";
    String actualErrorExcpetionDescription = "un paramètre (URL, header, JSON, etc.) est manquant";


    @Test
    void givenValidError_whenErrorException_thenGetCode(){
        String getCode = errorException.getCode();

        assertEquals(getCode, actualErrorExcpetionName);
    }
    @Test
    void givenValidError_whenErrorException_thenGetDescription() {
        String getDescription = errorException.getDescription();

        assertEquals(getDescription, actualErrorExcpetionDescription);

    }

}
