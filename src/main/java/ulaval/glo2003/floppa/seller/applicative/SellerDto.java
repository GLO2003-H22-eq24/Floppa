package ulaval.glo2003.floppa.seller.applicative;

import java.time.LocalDate;

public class SellerDto {
	private final String name;
	private final String bio;
	private final LocalDate birthDate;

	public SellerDto(String name, String bio, LocalDate birthDate) {
		this.name = name;
		this.bio = bio;
		this.birthDate = birthDate;
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
}
