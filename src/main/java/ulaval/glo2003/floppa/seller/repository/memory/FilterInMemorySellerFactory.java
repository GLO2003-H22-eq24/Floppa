package ulaval.glo2003.floppa.seller.repository.memory;

import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.repository.memory.FilterInMemoryProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class FilterInMemorySellerFactory {
	private final FilterInMemoryProductFactory filterInMemoryProductFactory;

	public FilterInMemorySellerFactory(FilterInMemoryProductFactory filterInMemoryProductFactory) {
		this.filterInMemoryProductFactory = filterInMemoryProductFactory;
	}

	public List<Function<Seller, Boolean>> createConditionsSellerFunction(ConditionSellerDto conditionSellerDto){
		List<Function<Seller, Boolean>> sellerConditions = new ArrayList<>();
		this.addSellerIdCondition(conditionSellerDto.getSellerId(), sellerConditions);
		Optional.ofNullable(conditionSellerDto.getConditionProductDto())
				.ifPresent(conditionProductDto -> this.addProductFilterCondition(sellerConditions, conditionProductDto));
		this.addDefaultTrueCondition(sellerConditions);
		return sellerConditions;
	}

	private void addSellerIdCondition(String sellerId, List<Function<Seller, Boolean>> sellerConditions) {
		Optional.ofNullable(sellerId).ifPresent(id -> sellerConditions.add(otherSeller -> Objects.equals(sellerId, otherSeller.getId())));
	}

	private void addProductFilterCondition(List<Function<Seller, Boolean>> sellerConditions, ConditionProductDto conditionProductDto){
		List<Function<Product, Boolean>> productConditions = filterInMemoryProductFactory.createConditionsProductFunction(conditionProductDto);
		sellerConditions.add(otherSeller -> otherSeller
				.getProducts().stream()
				.anyMatch(product -> productConditions.stream()
						.allMatch(condition -> condition.apply(product))));
	}

	private void addDefaultTrueCondition(List<Function<Seller, Boolean>> sellerConditions){
		if (sellerConditions.isEmpty()) {
			sellerConditions.add(otherSeller -> true);
		}
	}
}
