package com.scrotify.flexicommerce.service;


import java.util.List;
import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;

/**
 * This class is used to for logging in to the application and to getting the orders by userId.
 * @author Vasavi
 * @author Anisha R
 * @since 2019-12-23
 *
 */
public interface UserService {

	/**
	 * This method is used for getting the list of orders for userId.
	 * 
	 * @param userId
	 * 
	 * @author Anisha R
	 * 
	 *         This service is used for getting the list of orders
	 *
	 * @return List<MyOrderResponseDto
	 * 
	 *         Here we are listing the orders with particular userId.
	 * @throws CommonException
	 *
	 */

	public List<MyOrderResponseDto> getOrders(Integer userId) throws CommonException;



	/**
	 * This method is used for logging in to the application.
	 * 
	 * @param userRequestDto The userRequestDto which contains userName and
	 *                       password.
	 * @return userResponseDto
	 */
	public UserResponseDto login(UserRequestDto userRequestDto);


}
