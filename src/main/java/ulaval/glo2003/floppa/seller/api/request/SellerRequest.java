package ulaval.glo2003.floppa.seller.api.request;

public class SellerRequest {
    private String name;
    private String bio;
    private String birthDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) { //used for serialisation
        this.bio = bio;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) { //used for serialisation
        this.birthDate = birthDate;
    }
}
