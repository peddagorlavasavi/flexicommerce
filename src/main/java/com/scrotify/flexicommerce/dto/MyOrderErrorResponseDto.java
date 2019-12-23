package com.scrotify.flexicommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderErrorResponseDto {

	private String message;
	private Integer statusCode;

}
