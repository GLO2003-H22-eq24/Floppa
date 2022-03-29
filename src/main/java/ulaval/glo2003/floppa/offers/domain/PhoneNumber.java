package ulaval.glo2003.floppa.offers.domain;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.regex.Pattern;

@Entity
public class PhoneNumber {
	@Transient
	private static final Pattern phoneValidator = Pattern.compile("^[\\+]?[(]?[0-9]{3}[)]?[-\\s\\.]?[0-9]{3}[-\\s\\.]?[0-9]{4,6}$");
	private final String phoneNumberString;

	public PhoneNumber(String phoneNumberString) throws ErrorException {
		if (!phoneValidator.matcher(phoneNumberString).matches()){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.phoneNumberString = phoneNumberString;
	}

	@Override
	public String toString() {
		return phoneNumberString;
	}
}
