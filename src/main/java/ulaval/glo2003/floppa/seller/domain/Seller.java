package ulaval.glo2003.floppa.seller.domain;

public class Seller {
    //TODO: Ajouter les annotations JSON
    String name;
    String bio;
    int age;

    public Seller(String name, String bio, int age) {
        this.name = name;
        this.bio = bio;
        this.age = age;
    }
}
