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
        // Vérifie que les paramètres nécessaires sont présent
        if (isNull(sellerDto.getName()) || isNull(sellerDto.getBio()) || isNull(sellerDto.getBirthDate())){
            throw new ErrorException(ErrorCode.MISSING_PARAMETER);
        }

        // Vérifie que les paramètres nécessaires contiennent une valeur
        if (sellerDto.getBio().isBlank() || sellerDto.getName().isBlank()) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }

        try {
            // Vérifie que la date de naissance est dans le bon format
            LocalDate birthDate = LocalDate.parse(sellerDto.getBirthDate(), DateTimeFormatter.ISO_DATE);
            int age = Period.between(birthDate, LocalDate.now()).getYears();

            return new Seller(sellerDto.getName(), sellerDto.getBio(), age);
        } catch (DateTimeParseException e) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }
    }
}
