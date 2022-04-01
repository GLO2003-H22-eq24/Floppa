package ulaval.glo2003.floppa.offers.api.message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyerResponseTest {

    BuyerResponse buyerResponse;

    @BeforeEach
    void givenValidBuyerResponse() {
        buyerResponse = new BuyerResponse("Name", "EmailTest@test.ca", "418-000-0000");
    }

    @Test
    void givenValidBuyerResponse_whenNameValid_thenGetNameIsValid() {
        String validName = "Name";
        String testedName = buyerResponse.getName();

        assertEquals(validName, testedName);
    }
    @Test
    void givenValidBuyerResponse_whenEmailValid_thenGetEmailIsValid() {
        String validEmail = "Name";
        String testedEmail = buyerResponse.getEmail();

        assertEquals(validEmail, testedEmail);
    }
    @Test
    void givenValidBuyerResponse_whenPhoneNumberValid_thenGetPhoneNumberIsValid() {
        String validPhoneNumber = "Name";
        String testedPhoneNumber = buyerResponse.getPhoneNumber();

        assertEquals(validPhoneNumber, testedPhoneNumber);
    }

    @Test
    void givenValidName_whenSettingName_thenSetNameIsEqualToValidName() {
        String newValidName = "newName";

        buyerResponse.setName(newValidName);

        assertEquals(newValidName, buyerResponse.getName());
    }

    @Test
    void givenValidEmail_whenSettingEmail_thenSetEmailIsEqualToValidEmail() {
        String newValidEmail = "newEmailTest@test.ca";

        buyerResponse.setName(newValidEmail);

        assertEquals(newValidEmail, buyerResponse.getEmail());
    }

    @Test
    void givenValidPhoneNumber_whenSettingPhoneNumber_thenSetPhoneNumberIsEqualToValidPhoneNumber() {
        String newValidPhoneNumber = "418-999-9999";

        buyerResponse.setName(newValidPhoneNumber);

        assertEquals(newValidPhoneNumber, buyerResponse.getPhoneNumber());
    }

}
