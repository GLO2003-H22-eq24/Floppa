package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.app.domain.DateUtil;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.applicative.SellerDto;

import java.time.*;
import java.util.UUID;

public class SellerFactory {
	public Seller createSeller(SellerDto sellerDto) throws ErrorException {
		validateAge(sellerDto.getBirthDate());
		return new Seller(sellerDto.getName(), sellerDto.getBio(), sellerDto.getBirthDate(),
				UUID.randomUUID().toString(), DateUtil.getUtcNow());
	}

	private void validateAge(LocalDate birthDate) throws ErrorException {
		if (this.computeAge(birthDate) < 18) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
	}

	private int computeAge(LocalDate birthDate) {
		return Period.between(birthDate, DateUtil.getUtcNow().toLocalDate()).getYears();
	}
}
