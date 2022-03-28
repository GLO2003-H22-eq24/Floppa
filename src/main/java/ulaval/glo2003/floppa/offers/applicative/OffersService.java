package ulaval.glo2003.floppa.offers.applicative;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.domain.Offers;
import ulaval.glo2003.floppa.offers.domain.OffersFactory;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerAssembleur;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

public class OffersService {
	private final SellerRepository sellerRepository;
	private final ConditionSellerAssembleur conditionSellerAssembleur;
	private final OffersFactory offersFactory;

	public OffersService(SellerRepository sellerRepository, ConditionSellerAssembleur conditionSellerAssembleur, OffersFactory offersFactory) {
		this.sellerRepository = sellerRepository;
		this.conditionSellerAssembleur = conditionSellerAssembleur;
		this.offersFactory = offersFactory;
	}

	public void addOfferToProduct(String productId, OffersDto offersDto) throws ErrorException {
		Offers offers = this.offersFactory.createOffers(offersDto);
		ConditionProductDto conditionProductDto = new ConditionProductDtoBuilder().addProductId(productId).build();
		ConditionSellerDto conditionSellerDto = conditionSellerAssembleur.toDto(conditionProductDto);
		Product product = sellerRepository.findProducts(conditionSellerDto).stream().findFirst().orElseThrow(() -> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
		product.addOffer(offers);
		sellerRepository.updateProduct(product);
	}
}
