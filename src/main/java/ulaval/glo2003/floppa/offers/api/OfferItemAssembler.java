package ulaval.glo2003.floppa.offers.api;

import ulaval.glo2003.floppa.offers.api.message.BuyerResponse;
import ulaval.glo2003.floppa.offers.api.message.OfferItemResponse;
import ulaval.glo2003.floppa.offers.domain.Offers;

import java.util.List;
import java.util.stream.Collectors;

public class OfferItemAssembler {
	private final BuyerAssembler buyerAssembler;

	public OfferItemAssembler(BuyerAssembler buyerAssembler) {
		this.buyerAssembler = buyerAssembler;
	}

	public List<OfferItemResponse> toResponse(List<Offers> offers) {
		return offers.stream().map(this::toResponse).collect(Collectors.toList());
	}

	public OfferItemResponse toResponse(Offers offer) {
		BuyerResponse buyer = buyerAssembler.toResponse(offer);
		return new OfferItemResponse(offer.getId(), offer.getCreatedDate(), offer.getOfferAmount(), offer.getMessage(), buyer);
	}
}
