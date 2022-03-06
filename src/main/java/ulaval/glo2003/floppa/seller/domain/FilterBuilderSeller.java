package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.product.domain.Product;

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

	public FilterBuilderSeller addProductFilterCondition(List<Function<Product, Boolean>> productConditions){
		this.sellerConditions.add(otherSeller -> otherSeller
				.getProducts().stream()
				.anyMatch(product -> productConditions.stream()
						.allMatch(condition -> condition.apply(product))));
		return this;
	}

	public List<Function<Seller, Boolean>> build(){
		return this.sellerConditions;
	}
}
