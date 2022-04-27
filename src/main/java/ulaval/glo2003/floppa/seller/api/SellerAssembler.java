package ulaval.glo2003.floppa.seller.api;

import ulaval.glo2003.floppa.app.domain.DateUtil;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.api.ProductAssembler;
import ulaval.glo2003.floppa.product.api.response.ProductResponse;
import ulaval.glo2003.floppa.seller.api.request.SellerRequest;
import ulaval.glo2003.floppa.seller.api.response.SellerResponse;
import ulaval.glo2003.floppa.seller.applicative.SellerDto;
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

    public SellerDto toDto(SellerRequest sellerRequest) throws ErrorException {
        assertNotNullParams(sellerRequest);
        assertNotBlankParams(sellerRequest);
        try {
            LocalDate birthDate = LocalDate.parse(sellerRequest.getBirthDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            return new SellerDto(sellerRequest.getName(), sellerRequest.getBio(), birthDate);
        } catch (DateTimeParseException e) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }
    }

    public SellerResponse toResponse(Seller seller, boolean detailledItems) {
        List<ProductResponse> productResponse = seller.getProducts().stream().map(val -> productAssembler.toResponse(val, detailledItems)).collect(Collectors.toList());
        SellerResponse sellerResponse = new SellerResponse(seller.getId(), seller.getName(), DateUtil.toISO8601WithHours(seller.getCreatedDate()), seller.getBio(), productResponse);
        if (detailledItems) {
            sellerResponse.setBirthDate(DateUtil.toISO8601WithNoHours(seller.getBirthDate()));
        }
        return sellerResponse;
    }


    private void assertNotBlankParams(SellerRequest sellerRequest) throws ErrorException {
        if (sellerRequest.getBio().isBlank() || sellerRequest.getName().isBlank()) {
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }
    }

    private void assertNotNullParams(SellerRequest sellerRequest) throws ErrorException {
        if (isNull(sellerRequest.getName()) || isNull(sellerRequest.getBio()) || isNull(sellerRequest.getBirthDate())) {
            throw new ErrorException(ErrorCode.MISSING_PARAMETER);
        }
    }
}
