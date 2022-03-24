package ulaval.glo2003.floppa.seller.repository.mongo;

import dev.morphia.annotations.Id;
import dev.morphia.annotations.Property;
import ulaval.glo2003.floppa.product.domain.Product;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class SellerMapping {
	public static final String NAME ="name";
	public static final String BIO = "bio";
	//TODO: continuer le mapping

	private SellerMapping() { //hide useless constructor
	}
}
