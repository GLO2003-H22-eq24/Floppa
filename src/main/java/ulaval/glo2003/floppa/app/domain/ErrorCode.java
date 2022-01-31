package ulaval.glo2003.floppa.app.domain;

import jakarta.json.bind.annotation.JsonbProperty;

public enum ErrorCode {
	MISSING_PARAMETER("un paramètre (URL, header, JSON, etc.) est manquant", 400),
	INVALID_PARAMETER("un paramètre (URL, header, JSON, etc.) est invalide (vide, négatif, trop long. etc.)", 400),
	ITEM_NOT_FOUND("un élément est introuvable (id invalide ou inexistant)", 404);
	private final String description;

	private final int statusCode;
	ErrorCode(String description, int statusCode) {
		this.description = description;
		this.statusCode = statusCode;
	}

	@JsonbProperty("description")
	public String getDescription() {
		return description;
	}
	public int getStatusCode() {
		return statusCode;
	}
}
