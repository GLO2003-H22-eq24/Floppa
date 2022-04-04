package ulaval.glo2003.floppa.seller.domain;

import dev.morphia.annotations.*;
import ulaval.glo2003.floppa.product.domain.Product;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static ulaval.glo2003.floppa.seller.repository.mongo.SellerMapping.*;

@Entity
public class Seller {
    @Property(value = NAME)
    private final String name;
    @Property(value = BIO)
    private String bio;
    @Property(value = BIRTHDATE)
    private final LocalDate birthDate;
    @Id
    private final String id;
    @Property(value = CREATED_DATE)
    private final LocalTime createdDate;
    @Property(value = PRODUCTS)
    private List<Product> products;

    public Seller(String name, String bio, LocalDate birthDate, String id, LocalTime createdDate) {
        this.birthDate = birthDate;
        this.name = name;
        this.bio = bio;
        this.createdDate = createdDate;
        this.products = new ArrayList<>();
        this.id = id;
    }

	public String getId() {
        return this.id;
	}

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public LocalTime getCreatedDate() {
        return createdDate;
    }

    public List<Product> getProducts() {
        Optional.ofNullable(this.products).orElseGet(() -> this.products = new ArrayList<>());
        return this.products;
    }

    public void updateProduct(Product product) {
        List<Product> updatedProducts = this.getProducts().stream().map(otherProduct -> {
            if (otherProduct.getId().equals(product.getId())) {
                return product;
            } else {
                return otherProduct;
            }
        }).collect(Collectors.toList());
        this.products.clear();
        this.products.addAll(updatedProducts);
    }
}
