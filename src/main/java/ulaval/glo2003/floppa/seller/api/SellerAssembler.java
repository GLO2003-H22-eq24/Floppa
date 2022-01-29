package ulaval.glo2003.floppa.seller.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import static java.util.Objects.isNull;

public class SellerAssembler {
    public Seller fromDto(SellerDto sellerDto) throws ErrorException {
        //TODO: faire la validation sur les champs non-null et le format de la date
        // Dans les assembleur on fait juste de la validation de création d'objet.
        // raise new exception si cela ne respecte pas la création d'un Seller.

        // Vérifie que les paramètres nécessaires sont présent
        if (isNull(sellerDto.getName()) || isNull(sellerDto.getBio()) || isNull(sellerDto.getBirthDate())){
            throw new ErrorException(ErrorCode.MISSING_PARAM);
        }

        // Vérifie que les paramètres nécessaires contiennent une valeur
        if (sellerDto.getBio().isBlank() || sellerDto.getName().isBlank()) {
            throw new ErrorException(ErrorCode.INVALID_PARAM);
        }

        int age = 0;
        try {
            // Vérifie que la date de naissance est dans le bon format
            LocalDate birthDate = LocalDate.parse(sellerDto.getBirthDate(), DateTimeFormatter.ISO_DATE);
            age = Period.between(birthDate, LocalDate.now()).getYears();
            // Vérifie que le vendeur à 18 ans ou plus
            if (age < 18){
                throw new ErrorException(ErrorCode.INVALID_PARAM);
            }
        } catch (DateTimeParseException e) {
            throw new ErrorException(ErrorCode.INVALID_PARAM);
        }

        return new Seller(sellerDto.getName(), sellerDto.getBio(), age);
    }
}
