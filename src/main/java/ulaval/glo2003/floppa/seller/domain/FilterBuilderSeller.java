package ulaval.glo2003.floppa.seller.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

public class FilterBuilderSeller {
	private final List<Function<Seller, Boolean>> sellerConditions = new ArrayList<>();

	public FilterBuilderSeller addSellerIdCondition(String sellerId) {
		this.sellerConditions.add(otherSeller -> Objects.equals(sellerId, otherSeller.getId()));
		return this;
	}

	public List<Function<Seller, Boolean>> build(){
		return this.sellerConditions;
	}
}
