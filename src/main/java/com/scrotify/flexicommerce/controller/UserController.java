package com.scrotify.flexicommerce.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.exception.UserNotFoundException;
import com.scrotify.flexicommerce.service.UserService;
import com.scrotify.flexicommerce.utils.ApiConstant;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.scrotify.flexicommerce.dto.MyOrderErrorResponseDto;
import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.exception.CommonException;

/**
 * This class is used for logging in to the application and getting the orders
 * of particular user. These are the implementations of user Service.
 * 
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@RestController
@RequestMapping("")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class UserController {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

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
		logger.info("Get list of my orders details");
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

	/**
	 * @author Vasavi 
	 * This method is used for logging into the application.
	 * 
	 * @param userRequestDto which contains userName and password.
	 * @return userResponseDto
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto)
			throws UserNotFoundException {
		logger.info("Inside UserController: login method");
		UserResponseDto userResponseDto = userService.login(userRequestDto);
		if (userRequestDto != null) {
			logger.error("checking userRequestDto is exists or not");
			userResponseDto.setMessage(ApiConstant.SUCCESS);
			userResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
			return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
		} else {
			userResponseDto.setMessage(ApiConstant.FAILED);
			userResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
			return new ResponseEntity<>(userResponseDto, HttpStatus.NOT_FOUND);
		}

	}
}
