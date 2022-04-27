package ulaval.glo2003.floppa.seller.repository.mongo;

import dev.morphia.Datastore;
import dev.morphia.query.experimental.filters.Filter;
import dev.morphia.query.experimental.filters.Filters;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.ConditionProductDtoBuilder;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.repository.memory.FilterInMemoryProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SellerRepositoryMongo implements SellerRepository {

	private final Datastore datastore;
	private final MorphiaFilterSellerFactory morphiaFilterSellerFactory;
	private final FilterInMemoryProductFactory filterInMemoryProductFactory;

	public SellerRepositoryMongo(Datastore datastore, MorphiaFilterSellerFactory morphiaFilterSellerFactory, FilterInMemoryProductFactory filterInMemoryProductFactory) {
		this.datastore = datastore;
		this.morphiaFilterSellerFactory = morphiaFilterSellerFactory;
		this.filterInMemoryProductFactory = filterInMemoryProductFactory;
	}

	@Override
	public void saveSeller(Seller seller) {
		datastore.save(seller);
	}

	@Override
	public Seller retrieveSeller(String sellerId) throws ErrorException {
		return Optional.ofNullable(datastore.find(Seller.class)
				.filter(Filters.eq(SellerMapping.ID, sellerId)).first()).orElseThrow(()-> new ErrorException(ErrorCode.ITEM_NOT_FOUND));
	}

	@Override
	public List<Seller> retrieveSeller(ConditionSellerDto sellerConditions) {
		List<Filter> conditionsSellerFunction = this.morphiaFilterSellerFactory.createConditionsSellerFunction(sellerConditions);
		return datastore.find(Seller.class).filter(conditionsSellerFunction.toArray(Filter[]::new)).iterator().toList();
	}

	@Override
	public List<Product> findProducts(ConditionSellerDto conditionSellerDto) {
		List<Filter> conditionsSellerFunction = this.morphiaFilterSellerFactory.createConditionsSellerFunction(conditionSellerDto);
		return datastore.find(Seller.class)
				.filter(conditionsSellerFunction.toArray(Filter[]::new)).iterator().toList()
				.stream().map(Seller::getProducts).flatMap(List::stream)
				.filter(product -> filterInMemoryProductFactory
						.createConditionsProductFunction(conditionSellerDto.getConditionProductDto()).stream().allMatch(func -> func.apply(product)))
				.collect(Collectors.toList());
	}

	@Override
	public void updateProduct(Product product) {
		ConditionSellerDto conditionSellerDto = new ConditionSellerDto(new ConditionProductDtoBuilder().addProductId(product.getId()).build());
		List<Filter> conditionsSellerFunction = this.morphiaFilterSellerFactory.createConditionsSellerFunction(conditionSellerDto);
		Seller seller = datastore.find(Seller.class).filter(conditionsSellerFunction.toArray(Filter[]::new)).first();
		Optional.ofNullable(seller).ifPresent(val-> {
			seller.updateProduct(product);
			datastore.save(seller);
		});
	}

	@Override
	public boolean checkPersistenceState() {
		try {
			callDb();
			return true;
		}catch (Exception e){
			return false;
		}
	}

	private void callDb() throws RuntimeException {
		this.datastore.getDatabase().listCollections().first();
	}
}
