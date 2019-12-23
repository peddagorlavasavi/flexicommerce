package com.scrotify.flexicommerce.service;

import com.scrotify.flexicommerce.dto.UserOrderRequestDto;
import com.scrotify.flexicommerce.dto.UserOrderResponseDto;

public interface UserOrderService {
	public UserOrderResponseDto buyProduct(Integer userId, Integer productId, UserOrderRequestDto userOrderRequestDto);
}
