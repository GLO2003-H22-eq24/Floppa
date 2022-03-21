package ulaval.glo2003.floppa.offers.domain;

import org.apache.commons.validator.routines.EmailValidator;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

public class Email {
	private static final EmailValidator emailValidator = EmailValidator.getInstance();
	private final String emailString;

	public Email(String emailString) throws ErrorException {
		if (!emailValidator.isValid(emailString)){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.emailString = emailString;
	}

	@Override
	public String toString() {
		return emailString;
	}
}
