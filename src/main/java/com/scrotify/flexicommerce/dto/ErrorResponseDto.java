package com.scrotify.flexicommerce.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter

public class ErrorResponseDto {
	private String message;
	private Integer statusCode;

}
