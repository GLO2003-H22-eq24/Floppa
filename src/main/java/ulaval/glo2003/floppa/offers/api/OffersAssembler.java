package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.message.OfferItemResponse;
import ulaval.glo2003.floppa.offers.api.message.OffersRequest;
import ulaval.glo2003.floppa.offers.api.message.OffersResponse;
import ulaval.glo2003.floppa.offers.applicative.OffersDto;
import ulaval.glo2003.floppa.offers.domain.Email;
import ulaval.glo2003.floppa.offers.domain.PhoneNumber;
import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

public class OffersAssembler {
	private final OfferItemAssembler offerItemAssembler;

	public OffersAssembler(OfferItemAssembler offerItemAssembler) {
		this.offerItemAssembler = offerItemAssembler;
	}

	public OffersResponse toResponse(Product product, boolean detailledItems){
		Double mean = Optional.ofNullable(product.computeMeanOffers()).map(this::computeAs2DecimalPoint).orElse(null);
		Double min = null;
		Double max = null;
		List<OfferItemResponse> items = null;
		if (detailledItems){
			min = Optional.ofNullable(product.computeMinOffers()).map(this::computeAs2DecimalPoint).orElse(null);
			max = Optional.ofNullable(product.computeMaxOffers()).map(this::computeAs2DecimalPoint).orElse(null);
			items = Optional.ofNullable(product.getOffers()).filter(offers -> !offers.isEmpty()).map(offerItemAssembler::toResponse).orElse(null);
		}

		return new OffersResponse(mean, product.computeNumberOfOffers(), min, max, items);
	}

	private double computeAs2DecimalPoint(Double mean) {
		return BigDecimal.valueOf(mean).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public OffersDto toDto(OffersRequest offersRequest) throws ErrorException {
		Double amount = Optional.ofNullable(offersRequest.getAmount()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		String name = Optional.ofNullable(offersRequest.getName()).filter(val -> ! val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		Email email = new Email(Optional.ofNullable(offersRequest.getEmail()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER)));
		PhoneNumber phoneNumber = new PhoneNumber(Optional.ofNullable(offersRequest.getPhoneNumber()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER)));
		String message = Optional.ofNullable(offersRequest.getMessage()).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
		return new OffersDto(name, email, phoneNumber, amount, message);
	}
}
