package com.scrotify.flexicommerce.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.utils.ApiConstant;

@Service
public class UserServiceImpl implements UserService {

	/**
	 * This will inject all the implementations of the userOrderRepository.
	 */

	@Autowired
	UserOrderRepository userOrderRepository;

	/**
	 * This API is used to search the products in productList
	 * 
	 * @param userId
	 *
	 * @return List<MyOrderResponseDto>
	 * 
	 *         This returns the list of orders of by giving the userId
	 * 
	 * @author Anisha R
	 * @throws CommonException
	 * 
	 */

	@Override
	public List<MyOrderResponseDto> getOrders(Integer userId) throws CommonException {
		List<UserOrder> orderList = userOrderRepository.findByUserUserId(userId);
		if (orderList.isEmpty()) {
			throw new CommonException(ApiConstant.USERID_NOT_FOUND_MESSAGE);
		} else {
			List<MyOrderResponseDto> orders = new ArrayList<>();
			orderList.stream().forEach(userOrder -> {
				MyOrderResponseDto myOrderResponseDto = new MyOrderResponseDto();
				myOrderResponseDto.setAmount(userOrder.getAmount());
				myOrderResponseDto.setOrderedDate(userOrder.getOrderedDate());
				myOrderResponseDto.setOrderId(userOrder.getOrderId());
				myOrderResponseDto.setProductId(userOrder.getProduct().getProductId());
				myOrderResponseDto.setQuantity(userOrder.getQuantity());
				myOrderResponseDto.setUserId(userOrder.getUser().getUserId());
				orders.add(myOrderResponseDto);
			});
			return orders;
		}
	}

}
