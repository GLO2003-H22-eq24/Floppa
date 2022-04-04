package ulaval.glo2003.floppa.offers.api.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OfferItemResponseTest {

    OfferItemResponse offerItemResponse;

    @BeforeEach
    void givenValidOfferItemResponse() {
        String id = "id";
        LocalTime createdAt = LocalTime.now();
        Double amount = 10.0;
        String message = "Un message avec plus de 100 charactere ici pour permettre que le message soit valide. Ceci est un test";
        String buyerResponseName = "Name";
        String buyerResponseEmail = "EmailTest@test.ca";
        String buyerResponsePhoneNumber = "418-000-0000";
        BuyerResponse buyer = new BuyerResponse(buyerResponseName, buyerResponseEmail, buyerResponsePhoneNumber);
        offerItemResponse = new OfferItemResponse(id, createdAt, amount, message, buyer);
    }

    @Test
    void givenValidOfferItemResponse_whenIdValid_thenGetIdIsValid() {
        String validId = "id";
        String testedId = offerItemResponse.getId();

        assertEquals(validId, testedId);
    }

    @Test
    void givenValidOfferItemResponse_whenAmountValid_thenGetAmountIsValid() {
        Double validAmount = 10.0;
        Double testedAmount = offerItemResponse.getAmount();

        assertEquals(validAmount, testedAmount);
    }

    @Test
    void givenValidOfferItemResponse_whenMessageValid_thenGetMessageIsValid() {
        String validMessage = "Un message avec plus de 100 charactere ici pour permettre que le message soit valide. Ceci est un test";
        String testedMessage = offerItemResponse.getMessage();

        assertEquals(validMessage, testedMessage);
    }

    @Test
    void givenValidOfferItemResponse_whenPhoneNumberValid_thenGetPhoneNumberIsValid() {
        String validBuyerResponseName = "Name";
        String validBuyerResponseEmail = "EmailTest@test.ca";
        String validBuyerResponsePhoneNumber = "418-000-0000";
        BuyerResponse validBuyer = new BuyerResponse(validBuyerResponseName, validBuyerResponseEmail, validBuyerResponsePhoneNumber);

        BuyerResponse testedBuyer = offerItemResponse.getBuyer();

        assertEquals(validBuyer.getPhoneNumber(), testedBuyer.getPhoneNumber());
    }

    @Test
    void givenValidId_whenSettingId_thenSetIdIsEqualToValidId() {
        String newValidId = "id_2";

        offerItemResponse.setId(newValidId);

        assertEquals(newValidId, offerItemResponse.getId());
    }

    @Test
    void givenValidCreatedAt_whenSettingCreatedAt_thenSetCreatedAtIsEqualToValidCreatedAt() {
        String newValidMessage = "id_2";

        offerItemResponse.setMessage(newValidMessage);

        assertEquals(newValidMessage, offerItemResponse.getMessage());
    }
    @Test
    void givenValidAmount_whenSettingAmount_thenSetAmountIsEqualToValidAmount() {
        Double newValidAmount = 11.0;

        offerItemResponse.setAmount(newValidAmount);

        assertEquals(newValidAmount, offerItemResponse.getAmount());

    }
    @Test
    void givenValidMessage_whenSettingMessage_thenSetMessageIsEqualToValidMessage() {
        String newValidMessage = "Un message avec plus de 100 charactere ici pour permettre que le message soit valide. Ceci est un nouveau test un peu plus long";

        offerItemResponse.setMessage(newValidMessage);

        assertEquals(newValidMessage, offerItemResponse.getMessage());

    }
    @Test
    void givenValidBuyer_whenSettingBuyer_thenSetBuyerIsEqualToValidBuyer() {
        String newValidBuyerResponseName = "newName";
        String newValidBuyerResponseEmail = "newEmailTest@test.ca";
        String newValidBuyerResponsePhoneNumber = "418-999-9999";
        BuyerResponse newValidBuyer = new BuyerResponse(newValidBuyerResponseName, newValidBuyerResponseEmail, newValidBuyerResponsePhoneNumber);

        offerItemResponse.setBuyer(newValidBuyer);

        assertEquals(newValidBuyer, offerItemResponse.getBuyer());

    }
}
