package com.scrotify.flexicommerce.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MyOrderResponseDto {

	private Integer quantity;
	private Double amount;
	private String description;
	private String imageUrl;
	private String userName;
	private String productName;

}
