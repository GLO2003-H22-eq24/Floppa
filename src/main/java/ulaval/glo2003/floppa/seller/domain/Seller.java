package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;
import ulaval.glo2003.floppa.product.domain.Product;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.UUID;

import static java.time.LocalTime.now;

public class Seller {
    private final String name;
    private String bio;
    private final LocalDate birthDate;
    private final String id;
    private final LocalTime createdDate;
    private final List<Product> products;

    public Seller(String name, String bio, LocalDate birthDate) throws ErrorException{
        this.birthDate = birthDate;
        if (this.computeAge() < 18){
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);

        }
        this.name = name;
        this.bio = bio;
        this.createdDate = LocalTime.now(Clock.system(ZoneId.of("-05:00")));
        this.products = new ArrayList<>();
        this.id = UUID.randomUUID().toString();
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

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalTime getCreatedDate() {
        return createdDate;
    }

    public List<Product> getProducts() {
        return products;
    }
}
