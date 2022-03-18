package ulaval.glo2003.floppa.offers.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.regex.Pattern;

public class PhoneNumber {
	private static final Pattern phoneValidator = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
	private final String phoneNumber;

	public PhoneNumber(String phoneNumber) throws ErrorException {
		if (!phoneValidator.matcher(phoneNumber).matches()){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.phoneNumber = phoneNumber;
	}
}
