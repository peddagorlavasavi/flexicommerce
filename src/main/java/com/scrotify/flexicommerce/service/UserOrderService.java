package com.scrotify.flexicommerce.service;

import java.util.List;

import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.dto.UserOrderRequestDto;
import com.scrotify.flexicommerce.dto.UserOrderResponseDto;
import com.scrotify.flexicommerce.exception.CommonException;

public interface UserOrderService {
	
	
	public UserOrderResponseDto buyProduct(Integer userId, Integer productId, UserOrderRequestDto userOrderRequestDto) throws CommonException;
	
	
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

	public List<MyOrderResponseDto> getMyOrders(Integer userId) throws CommonException;
}
