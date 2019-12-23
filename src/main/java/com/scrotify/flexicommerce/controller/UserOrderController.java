package com.scrotify.flexicommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexicommerce.dto.UserOrderRequestDto;
import com.scrotify.flexicommerce.dto.UserOrderResponseDto;
import com.scrotify.flexicommerce.service.UserOrderService;
import com.scrotify.flexicommerce.utils.ApiConstant;

@RestController
@RequestMapping("")
@CrossOrigin
public class UserOrderController {
	private static final Logger logger = LoggerFactory.getLogger(UserOrderController.class);
	@Autowired
	UserOrderService userOrderService;

	@PostMapping("/users/{userId}/products/{productId}/orders")
	public ResponseEntity<UserOrderResponseDto> buyProduct(@PathVariable("userId") Integer userId,
			@PathVariable("productId") Integer productId, @RequestBody UserOrderRequestDto userOrderRequestDto) {

		logger.info("Inside UserOrderController : buyProduct method ");
		UserOrderResponseDto userOrderResponseDto = userOrderService.buyProduct(userId, productId, userOrderRequestDto);
		if (userId != null && productId != null) {
			logger.error("checking userId and productId are null or not");
			userOrderResponseDto = new UserOrderResponseDto();
			userOrderResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
			userOrderResponseDto.setStatusMessage(ApiConstant.SUCCESS);
			return new ResponseEntity<>(userOrderResponseDto, HttpStatus.OK);

		} else {
			userOrderResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
			userOrderResponseDto.setStatusMessage(ApiConstant.FAILURE);
			return new ResponseEntity<>(userOrderResponseDto, HttpStatus.NOT_FOUND);

		}

	}

}
