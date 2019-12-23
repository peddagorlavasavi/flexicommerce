package com.scrotify.flexicommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.exception.UserNotFoundException;
import com.scrotify.flexicommerce.service.UserServiceImpl;
import com.scrotify.flexicommerce.utils.ApiConstant;

/**
 * This class is used to do test operation for UserController
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserControllerTest.class);
	@InjectMocks
	UserController userController;
	@Mock
	UserServiceImpl userService;
	UserResponseDto userResponseDto = new UserResponseDto();
	UserRequestDto userRequestDto = new UserRequestDto();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userResponseDto.setMessage(ApiConstant.SUCCESS);
		userResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
		userResponseDto.setMessage(ApiConstant.FAILURE);
		userResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
	}

	@Test
	public void testLogin() throws UserNotFoundException {
		logger.info("Inside loginTest method");
		when(userService.login(userRequestDto)).thenReturn(userResponseDto);
		ResponseEntity<UserResponseDto> userResponseDto = userController.login(userRequestDto);
		assertEquals(ApiConstant.SUCCESS, userResponseDto.getBody().getMessage());

	}

	@Test
	public void testLoginNegative() throws UserNotFoundException {
		logger.info("Inside loginNegativeTest method");
		when(userService.login(null)).thenReturn(userResponseDto);
		ResponseEntity<UserResponseDto> userResponseDto = userController.login(null);
		assertEquals(ApiConstant.FAILED, userResponseDto.getBody().getMessage());

	}
}
