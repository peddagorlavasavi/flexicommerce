package com.scrotify.flexicommerce.service;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;

/**
 * This class is used to for logging in to the application and to getting the
 * orders by userId.
 * 
 * @author Vasavi
 * @author Anisha R
 * @since 2019-12-23
 *
 */
public interface UserService {

	/**
	 * This method is used for logging in to the application.
	 * 
	 * @param userRequestDto The userRequestDto which contains userName and
	 *                       password.
	 * @return userResponseDto
	 */
	public UserResponseDto login(UserRequestDto userRequestDto);

}
