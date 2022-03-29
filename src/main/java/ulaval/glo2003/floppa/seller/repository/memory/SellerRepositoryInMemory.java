package ulaval.glo2003.floppa.seller.repository.memory;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
import ulaval.glo2003.floppa.product.repository.memory.FilterInMemoryProductFactory;
import ulaval.glo2003.floppa.seller.domain.ConditionSellerDto;
import ulaval.glo2003.floppa.seller.domain.Seller;
import ulaval.glo2003.floppa.seller.domain.SellerRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class SellerRepositoryInMemory implements SellerRepository {
    private static final Logger LOGGER = Logger.getLogger(SellerRepositoryInMemory.class.getName());
    private final Map<String, Seller> sellersById;
    private final FilterInMemorySellerFactory conditionSellerFactory;
    private final FilterInMemoryProductFactory conditionProductFactory;

    public SellerRepositoryInMemory(Map<String, Seller> sellersById, FilterInMemorySellerFactory conditionSellerFactory, FilterInMemoryProductFactory conditionProductFactory) {
        this.sellersById = sellersById;
        this.conditionSellerFactory = conditionSellerFactory;
        this.conditionProductFactory = conditionProductFactory;
    }

    @Override
    public void saveSeller(Seller seller) {
        Optional.ofNullable(sellersById.put(seller.getId(), seller)).ifPresent(oldValue -> LOGGER.log(Level.SEVERE, "Erased seller with id: {0} ", oldValue));
        LOGGER.log(Level.INFO, "Saved seller with id: {0} ", seller.getId());
    }

    @Override
    public Seller retrieveSeller(String sellerId) throws ErrorException {
        return Optional.ofNullable(this.sellersById.get(sellerId))
                .orElseThrow(()->new ErrorException(ErrorCode.ITEM_NOT_FOUND));
    }

    @Override
    public List<Seller> retrieveSeller(ConditionSellerDto conditionSellerDto) {
        List<Function<Seller, Boolean>> sellerConditions = conditionSellerFactory.createConditionsSellerFunction(conditionSellerDto);
        return this.retrieveSellerBySellerConditions(sellerConditions);
    }

    @Override
    public List<Product> findProducts(ConditionSellerDto conditionSellerDto) {
        List<Function<Seller, Boolean>> sellerConditions = conditionSellerFactory.createConditionsSellerFunction(conditionSellerDto);
        List<Function<Product, Boolean>> productConditions = conditionProductFactory.createConditionsProductFunction(conditionSellerDto.getConditionProductDto());
        List<Product> products = retrieveProductsBySellerConditions(sellerConditions);
        return products.stream()
                .filter(product -> productConditions.stream()
                        .allMatch(productCondition -> productCondition.apply(product)))
                .collect(Collectors.toList());
    }

    @Override
    public void updateProduct(Product product) {
        //no need to implement, because in memory.
        LOGGER.log(Level.INFO, "Updated product with: {0} offers ", product.getOffers().size());
    }

    @Override
    public boolean checkPersistenceState() {
        return false; //Never true for in memory (volatile)
    }

    private List<Product> retrieveProductsBySellerConditions(List<Function<Seller, Boolean>> sellerConditions) {
        return this.retrieveSellerBySellerConditions(sellerConditions).stream()
                .map(Seller::getProducts)
                .flatMap(List::stream).collect(Collectors.toList());
    }

    private List<Seller> retrieveSellerBySellerConditions(List<Function<Seller, Boolean>> sellerConditions) {
        return this.sellersById.values().stream().filter(seller -> sellerConditions.stream().allMatch(condition -> condition.apply(seller))).collect(Collectors.toList());
    }
}
