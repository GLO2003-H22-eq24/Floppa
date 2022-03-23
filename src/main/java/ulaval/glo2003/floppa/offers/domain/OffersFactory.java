package ulaval.glo2003.floppa.offers.domain;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.applicative.OffersDto;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class OffersFactory {
	public Offers createOffers(OffersDto offersDto) throws ErrorException {
		return new Offers(offersDto.getName(), offersDto.getEmail(), offersDto.getPhoneNumber(),
				offersDto.getMessage(), offersDto.getAmount(),
				UUID.randomUUID().toString(), LocalTime.now(Clock.system(ZoneOffset.UTC)));
	}
}
