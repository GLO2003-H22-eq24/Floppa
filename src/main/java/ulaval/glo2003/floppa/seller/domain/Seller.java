package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.app.domain.ErrorCode;
import ulaval.glo2003.floppa.app.domain.ErrorException;

public class Seller {
    //TODO: Ajouter les annotations JSON
    private String name;
    private String bio;
    private int age;

    public Seller(String name, String bio, int age) throws ErrorException{
        if (age < 18){
            throw new ErrorException(ErrorCode.INVALID_PARAMETER);
        }
        this.name = name;
        this.bio = bio;
        this.age = age;
    }
}
