package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.offers.api.response.BuyerResponse;
import ulaval.glo2003.floppa.offers.domain.Offers;

public class BuyerAssembler {
	public BuyerResponse toResponse(Offers offer) {
		return new BuyerResponse(offer.getName(), offer.getEmail().toString(), offer.getPhoneNumber().toString());
	}
}
