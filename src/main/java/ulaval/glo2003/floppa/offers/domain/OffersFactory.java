package ulaval.glo2003.floppa.offers.domain;

import ulaval.glo2003.floppa.app.domain.DateUtil;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.applicative.OffersDto;

import java.util.UUID;

public class OffersFactory {
	public Offers createOffers(OffersDto offersDto) throws ErrorException {
		validateMessageLength(offersDto.getMessage());
		return new Offers(offersDto.getName(), offersDto.getEmail(), offersDto.getPhoneNumber(),
				offersDto.getMessage(), offersDto.getAmount(),
				UUID.randomUUID().toString(), DateUtil.getUtcNow());
	}

	private void validateMessageLength(String message) throws ErrorException {
		if (message.length() < 100) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}
}
