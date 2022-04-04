package ulaval.glo2003.floppa.offers.domain;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import org.apache.commons.validator.routines.EmailValidator;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
@Entity
public class Email {
	@Transient
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
