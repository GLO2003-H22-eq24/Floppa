package ulaval.glo2003.floppa.app.api;

import jakarta.ws.rs.core.HttpHeaders;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

import java.util.Optional;

public class HttpParamUtil {
	public static final String SELLER_ID_HEADER = "X-Seller-Id";

	private HttpParamUtil() {
	}

	public static String retrieveSellerIdFromHeaders(HttpHeaders headers) throws ErrorException {
		return fetchId(headers.getRequestHeaders().getFirst(SELLER_ID_HEADER));
	}

	public static String fetchId(String id) throws ErrorException {
		return Optional.ofNullable(id).filter(val -> !val.isBlank()).orElseThrow(() -> new ErrorException(ErrorCode.MISSING_PARAMETER));
	}
}
