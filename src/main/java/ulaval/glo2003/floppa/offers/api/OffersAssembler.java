package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.message.OffersDtoRequest;
import ulaval.glo2003.floppa.offers.api.message.OffersDtoResponse;
import ulaval.glo2003.floppa.offers.domain.Email;
import ulaval.glo2003.floppa.offers.domain.Offers;
import ulaval.glo2003.floppa.offers.domain.PhoneNumber;
import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

public class OffersAssembler {
	public OffersDtoResponse toDto(Product product){
		Double amount = Optional.ofNullable(product.computeMeanOffers()).map(this::computeAs2DecimalPoint).orElse(null);
		return new OffersDtoResponse(amount, product.computeNumberOfOffers());
	}

	private double computeAs2DecimalPoint(Double mean) {
		return BigDecimal.valueOf(mean).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public Offers fromDto(OffersDtoRequest offersDtoRequest) throws ErrorException {
		Double amount = Optional.ofNullable(offersDtoRequest.getAmount()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		String name = Optional.ofNullable(offersDtoRequest.getName()).filter(val -> ! val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		Email email = new Email(Optional.ofNullable(offersDtoRequest.getEmail()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER)));
		PhoneNumber phoneNumber = new PhoneNumber(Optional.ofNullable(offersDtoRequest.getPhoneNumber()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER)));
		String message = Optional.ofNullable(offersDtoRequest.getMessage()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		return new Offers(name, email, phoneNumber, message, amount);
	}
}
