package ulaval.glo2003.floppa.seller.domain;

import ulaval.glo2003.floppa.product.domain.ConditionProductDto;

public class ConditionSellerDto {
	private final ConditionProductDto conditionProductDto;
	private final String sellerId;

	public ConditionSellerDto(ConditionProductDto conditionProductDto, String sellerId) {
		this.conditionProductDto = conditionProductDto;
		this.sellerId = sellerId;
	}

	public ConditionProductDto getConditionProductDto() {
		return conditionProductDto;
	}

	public String getSellerId() {
		return sellerId;
	}
}
