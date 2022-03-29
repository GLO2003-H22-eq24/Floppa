package ulaval.glo2003.floppa.seller.repository.mongo;

import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.floppa.product.domain.ConditionProductDto;
import ulaval.glo2003.floppa.product.repository.mongo.MorphiaFilterProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MorphiaFilterSellerFactory {
	private final MorphiaFilterProductFactory morphiaFilterProductFactory;

	public MorphiaFilterSellerFactory(MorphiaFilterProductFactory morphiaFilterProductFactory) {
		this.morphiaFilterProductFactory = morphiaFilterProductFactory;
	}

	public List<Filter> createConditionsSellerFunction(ConditionSellerDto conditionSellerDto){
		List<Filter> sellerConditions = new ArrayList<>();
		this.addSellerIdCondition(conditionSellerDto.getSellerId(), sellerConditions);
		this.addProductFilterCondition(conditionSellerDto.getConditionProductDto(), sellerConditions);
		return sellerConditions;
	}

	private void addSellerIdCondition(String sellerId, List<Filter> sellerConditions) {
		Optional.ofNullable(sellerId).ifPresent(id -> sellerConditions.add(Filters.eq(SellerMapping.ID, sellerId)));
	}

	private void addProductFilterCondition(ConditionProductDto conditionProductDto, List<Filter> sellerConditions){
		List<Filter> productConditions = morphiaFilterProductFactory.createConditionsProductFunction(conditionProductDto);
		sellerConditions.add(Filters.elemMatch(SellerMapping.PRODUCTS, productConditions.toArray(Filter[]::new)));
	}
}
