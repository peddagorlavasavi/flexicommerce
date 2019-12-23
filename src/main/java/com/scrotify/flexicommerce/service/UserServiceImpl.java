package com.scrotify.flexicommerce.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.utils.StringConstant;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.utils.ApiConstant;

/**
 * @author Anisha R
 * @author Vasavi
 * @since 2019-12-23 
 * This class is used for logging in to the application and getting the orders by userId.
 *
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * This will inject all the implementations of the userOrderRepository.
	 */
  
  /**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserOrderRepository userOrderRepository;
  
  	/**
	 * The userRepository
	 */
	@Autowired
	UserRepository userRepository;

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

	/**
	 * This method is used for logging into the application.
	 * 
	 * @param userRequestDto the userRequestDto which contains userName and
	 *                       password.
	 * @return userResponseDto.
	 */
	@Override
	public UserResponseDto login(UserRequestDto userRequestDto) {
		logger.debug("Inside  UserServiceImpl :login method");
		UserResponseDto userResponseDto = null;
		User user = userRepository.findByUserNameAndPassword(userRequestDto.getUserName(),
				userRequestDto.getPassword());

		if (user != null) {
			userResponseDto = new UserResponseDto();
			userResponseDto.setUserId(user.getUserId());
			userResponseDto.setUserName(user.getUserName());
			userResponseDto.setMessage(StringConstant.SUCCESS);
			userResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		} else {
			userResponseDto = new UserResponseDto();
			userResponseDto.setMessage(StringConstant.FAILURE);
			userResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
		}
	
		return userResponseDto;

	}

}
