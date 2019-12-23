package com.scrotify.flexicommerce.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String userName;
	private String message;
	private Integer statusCode;

}
