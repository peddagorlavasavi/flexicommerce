package com.scrotify.flexicommerce.service;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.exception.UserNotFoundException;

/**
<<<<<<< HEAD
 * This class is used to for logging in to the application and to getting the
 * orders by userId.
 * 
=======
<<<<<<< HEAD
 * This class is used to for logging in to the application.
 * 
=======
 * This class is used to for logging in to the application and to getting the orders by userId.
>>>>>>> 7fbbab7c7222078565a21d86aa55e601a4bd163c
>>>>>>> 169cbf362b33330be099a7a4a896077a07690554
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
	 * @throws UserNotFoundException
	 */
	public UserResponseDto login(UserRequestDto userRequestDto) throws UserNotFoundException;

}
