package com.scrotify.flexicommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexicommerce.dto.MyOrderErrorResponseDto;
import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.service.UserService;
import com.scrotify.flexicommerce.utils.ApiConstant;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/users")
@CrossOrigin
public class UserController {

	/**
	 * These are the implementations of user Service.
	 */

	@Autowired
	UserService userService;

	/**
	 * 
	 * @author Anisha R
	 * 
	 *         The userId is given and getting the list of orders done.
	 * 
	 * @param userId
	 * 
	 *               The userId is passed to get the list of orders
	 * 
	 * @return List<MyOrderResponseDto>
	 * 
	 *         Here we are listing the list of orders done from userId.
	 * 
	 */

	@GetMapping("/{userId}/orders")
	public ResponseEntity<List<MyOrderResponseDto>> searchProducts(@PathVariable Integer userId)
			throws CommonException {
		log.info("Get list of my orders details");
		List<MyOrderResponseDto> userOrders = userService.getOrders(userId);
		if (userOrders.isEmpty()) {
			MyOrderErrorResponseDto myOrderErrorResponseDto = new MyOrderErrorResponseDto();
			myOrderErrorResponseDto.setMessage(ApiConstant.USERID_NOT_FOUND_MESSAGE);
			myOrderErrorResponseDto.setStatusCode(ApiConstant.USERID_NOT_FOUND_CODE);
			return new ResponseEntity<>(userOrders, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(userOrders, HttpStatus.OK);
		}

	}

}
