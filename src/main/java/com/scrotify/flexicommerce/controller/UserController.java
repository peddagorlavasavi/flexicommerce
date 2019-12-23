package com.scrotify.flexicommerce.controller;

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

/**
 * This class is used for logging in to the application.
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
	/**
	 * The userService
	 */
	@Autowired
	UserService userService;

	/**
	 * This method is used for logging into the application.
	 * 
	 * @param userRequestDto which contains userName and password.
	 * @return userResponseDto
	 * @throws UserNotFoundException
	 */
	@PostMapping("/login")
	public ResponseEntity<UserResponseDto> login(@RequestBody UserRequestDto userRequestDto) throws UserNotFoundException {
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
