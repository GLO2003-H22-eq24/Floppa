package ulaval.glo2003.floppa.seller.domain;

import dev.morphia.annotations.Embedded;
import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Id;
import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Seller {
    private final String name;
    private String bio;
    private final LocalDate birthDate;
    @Id
    private final String id;
    private final LocalTime createdDate;
    private final List<Product> products;

    public Seller(String name, String bio, LocalDate birthDate, String id, LocalTime createdDate) throws ErrorException{
        this.birthDate = birthDate;
        if (this.computeAge() < 18){
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);

        }
        this.name = name;
        this.bio = bio;
        this.createdDate = createdDate;
        this.products = new ArrayList<>();
        this.id = id;
    }


	public String getId() {
        return this.id;
	}

    public int computeAge(){
        return Period.between(this.birthDate, LocalDate.now()).getYears();
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
        return products;
    }
}
