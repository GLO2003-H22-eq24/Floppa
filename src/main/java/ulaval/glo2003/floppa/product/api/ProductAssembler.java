package ulaval.glo2003.floppa.product.api;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.offers.api.OffersAssembler;
import ulaval.glo2003.floppa.offers.api.OffersDto;
import ulaval.glo2003.floppa.product.api.message.ProductCreationDtoRequest;
import ulaval.glo2003.floppa.product.domain.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.format.DateTimeParseException;

public class ProductAssembler {
	private final OffersAssembler offersAssembler;

	public ProductAssembler(OffersAssembler offersAssembler) {
		this.offersAssembler = offersAssembler;
	}

	public ProductDto toDto(Product product){
		OffersDto offersDto = offersAssembler.toDto(product);
		Double amount = BigDecimal.valueOf(product.getSuggestedPrice()).setScale(2, RoundingMode.HALF_UP).doubleValue();
		return new ProductDto(product.getId(), product.getCreatedDate(), product.getTitle(), product.getDescription(), amount, offersDto);
	}

	public Product fromDto(ProductCreationDtoRequest productCreationDtoRequest) throws ErrorException {

// Vérifie que les paramètres nécessaires sont présent

		if (isNull(productCreationDtoRequest.getTitle()) || isNull(productCreationDtoRequest.getDescription()) || isNull(productCreationDtoRequest.getCategories()) || isNull(productCreationDtoRequest.getSuggestedPrice()) ) {
			throw new ErrorException(ErrorCode.MISSING_PARAMETER);
		}
		// Vérifie que les paramètres nécessaires contiennent une valeur
		if (productCreationDtoRequest.getTitle().isBlank() || productCreationDtoRequest.getDescription().isBlank() || productCreationDtoRequest.getCategories().isBlank() || productCreationDtoRequest.getSuggestedPrice().isBlank()) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);

		}

		try {

			//LocalDate birthDate = LocalDate.parse(sellerDtoRequest.getBirthDate(), DateTimeFormatter.ISO_DATE);
			return new Product(productCreationDtoRequest.getTitle(), productCreationDtoRequest.getDescription(), productCreationDtoRequest.getCategories(), productCreationDtoRequest.getSuggestedPrice());
		} catch (DateTimeParseException e) {
			throw new ErrorException(ErrorCode.INVALID_PARAMETER);
		}

	}

}
