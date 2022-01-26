package ulaval.glo2003.floppa.seller.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.domain.Seller;

public class SellerAssembler {
    public static Seller fromDto(SellerDto sellerDto) throws ErrorException{
        //TODO: faire la validation sur les champs non-null et le format de la date
        // Dans les assembleur on fait juste de la validation de création d'objet.
        // raise new exception si cela ne respecte pas la création d'un Seller.

        //EXEMPLE
        if (sellerDto.getBio().isBlank()) {
            throw new ErrorException(ErrorCode.INVALID_PARAM);
        }
        //EXEMPLE
        return new Seller();
    }
}
