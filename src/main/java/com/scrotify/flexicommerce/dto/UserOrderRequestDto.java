package com.scrotify.flexicommerce.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserOrderRequestDto {

	private Double transactionAmount;
	private String transactionDescription;
	private Long toAccount;
	private Long creditCardNumber;
	private Integer cvv;
	private Integer pin;
	private LocalDate expiryDate;
	private Integer quantity;

}
