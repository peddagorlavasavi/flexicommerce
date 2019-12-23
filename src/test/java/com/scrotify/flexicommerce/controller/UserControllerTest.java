package com.scrotify.flexicommerce.controller;

import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.exception.UserNotFoundException;
import com.scrotify.flexicommerce.service.UserServiceImpl;
import com.scrotify.flexicommerce.utils.ApiConstant;


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
	userResponseDto.setUserId(1);
	userResponseDto.setUserName("vasavi");
	
	}
	@Test
	public void testLogin() throws UserNotFoundException {
		logger.info("Inside loginTest method");
		when(userService.login(userRequestDto)).thenReturn(userResponseDto);
	}

}
