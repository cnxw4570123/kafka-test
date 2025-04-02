package com.kafka.order.presentation.dto.request;

import java.math.BigDecimal;

import com.kafka.order.application.dto.request.CreateOrderCommand;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateOrderRequest {
	private String userEmail;
	private String address;
	private String productInfo;
	private BigDecimal totalPrice;

	@Builder
	public CreateOrderRequest(String userEmail, String address, String productInfo, BigDecimal totalPrice) {
		this.userEmail = userEmail;
		this.address = address;
		this.productInfo = productInfo;
		this.totalPrice = totalPrice;
	}

	public CreateOrderCommand toCreateOrderCommand() {
		return CreateOrderCommand.builder()
			.userEmail(this.userEmail)
			.address(this.address)
			.productInfo(this.productInfo)
			.totalPrice(this.totalPrice)
			.build();
	}
}
