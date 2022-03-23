package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.applicative.SellerDto;

import java.time.Clock;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.util.UUID;

public class SellerFactory {
	public Seller createSeller(SellerDto sellerDto) throws ErrorException {
		return new Seller(sellerDto.getName(), sellerDto.getBio(), sellerDto.getBirthDate(),
				UUID.randomUUID().toString(), LocalTime.now(Clock.system(ZoneOffset.UTC)));
	}
}
