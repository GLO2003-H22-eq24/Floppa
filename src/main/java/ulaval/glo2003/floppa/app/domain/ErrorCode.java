package ulaval.glo2003.floppa.app.domain;

import jakarta.json.bind.annotation.JsonbProperty;

public enum ErrorCode {
	MISSING_PARAMETER("un paramètre (URL, header, JSON, etc.) est manquant"),
	INVALID_PARAMETER("un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)"),
	ITEM_NOT_FOUND("un élément est introuvable (id invalide ou inexistant)");
	private final String description;

	ErrorCode(String description) {
		this.description = description;
	}

	@JsonbProperty("description")
	public String getDescription() {
		return description;
	}
}
