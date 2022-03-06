package ulaval.glo2003.floppa.seller.repository;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;
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

    public SellerRepositoryInMemory(Map<String, Seller> sellersById) {
        this.sellersById = sellersById;
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
    public List<Product> findProductsByConditions(List<Function<Seller, Boolean>> sellerConditions, List<Function<Product, Boolean>> productConditions){
        List<Product> products = this.retrieveSeller(sellerConditions).stream()
                .map(Seller::getProducts)
                .flatMap(List::stream).collect(Collectors.toList());
        return products.stream()
                .filter(product -> productConditions.stream()
                        .allMatch(productCondition -> productCondition.apply(product)))
                .collect(Collectors.toList());
    }

    @Override
    public List<Seller> retrieveSeller(List<Function<Seller, Boolean>> filterConditions) {
        //TODO
        return null;
    }
}
