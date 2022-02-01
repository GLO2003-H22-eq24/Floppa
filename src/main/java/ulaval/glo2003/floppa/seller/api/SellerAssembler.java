package ulaval.glo2003.floppa.seller.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.ProductAssembler;
import ulaval.glo2003.floppa.product.api.ProductDto;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoRequest;
import ulaval.glo2003.floppa.seller.api.message.SellerDtoResponse;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

public class SellerAssembler {
    private final ProductAssembler productAssembler;

    public SellerAssembler(ProductAssembler productAssembler) {
        this.productAssembler = productAssembler;
    }

    public Seller fromDto(SellerDtoRequest sellerDtoRequest) throws ErrorException {
        // Vérifie que les paramètres nécessaires sont présent

        if (isNull(sellerDtoRequest.getName()) || isNull(sellerDtoRequest.getBio()) || isNull(sellerDtoRequest.getBirthDate())){
            throw new ErrorException(ErrorCode.MISSING_PARAMETER);
        }

        // Vérifie que les paramètres nécessaires contiennent une valeur
        if (sellerDtoRequest.getBio().isBlank() || sellerDtoRequest.getName().isBlank()) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);

        }

        try {
            // Vérifie que la date de naissance est dans le bon format
            LocalDate birthDate = LocalDate.parse(sellerDtoRequest.getBirthDate(), DateTimeFormatter.ISO_DATE);
            return new Seller(sellerDtoRequest.getName(), sellerDtoRequest.getBio(), birthDate);
        } catch (DateTimeParseException e) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }
    }

    public SellerDtoResponse toDto(Seller seller) {
        List<ProductDto> productDtos = seller.getProducts().stream().map(productAssembler::toDto).collect(Collectors.toList());
        return new SellerDtoResponse(seller.getId(), seller.getName(), seller.getCreatedDate(), seller.getBio(), productDtos);
    }
}
