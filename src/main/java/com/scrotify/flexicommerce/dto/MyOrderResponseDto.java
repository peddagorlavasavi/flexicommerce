package com.scrotify.flexicommerce.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderResponseDto {

	private Integer orderId;
	private Integer quantity;
	private Double amount;
	private LocalDate orderedDate;
	private Integer userId;
	private Integer productId;

}
