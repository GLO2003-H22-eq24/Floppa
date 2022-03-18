package ulaval.glo2003.floppa.offers.domain;

import org.apache.commons.validator.routines.EmailValidator;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

public class Email {
	private static final EmailValidator emailValidator = EmailValidator.getInstance();
	private final String email;

	public Email(String email) throws ErrorException {
		if (!emailValidator.isValid(email)){
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}
		this.email = email;
	}
}
