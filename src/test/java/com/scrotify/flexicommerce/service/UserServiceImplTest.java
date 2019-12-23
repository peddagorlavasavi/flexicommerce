package com.scrotify.flexicommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

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
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.exception.UserNotFoundException;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.utils.StringConstant;

/**
 * This class is used to do test operation for UserServiceImpl
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImplTest.class);
	@InjectMocks
	UserServiceImpl userService;
	@Mock
	UserRepository userRepository;
	UserRequestDto userRequestDto = new UserRequestDto();
	UserResponseDto userResponseDto = new UserResponseDto();
	User user = new User();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userResponseDto.setUserId(1);
		userResponseDto.setUserName("vasavi");
		userResponseDto.setMessage(StringConstant.SUCCESS);
		userResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		userResponseDto.setMessage(StringConstant.FAILURE);
		userResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
	}

	@Test
	public void testLogin() throws UserNotFoundException {
		logger.info("Inside loginTest method");
		Optional<User> optionalUser = Optional.of(user);
		when(userRepository.findByUserNameAndPassword(userRequestDto.getUserName(), userRequestDto.getPassword()))
				.thenReturn(optionalUser);
		UserResponseDto userResponseDto = userService.login(userRequestDto);
		assertEquals(StringConstant.SUCCESS, userResponseDto.getMessage());
	}

	@Test(expected = UserNotFoundException.class)
	public void testLoginNegative() throws UserNotFoundException {
		logger.info("Inside loginNegativeTest method");
		when(userRepository.findByUserNameAndPassword(userRequestDto.getUserName(), userRequestDto.getPassword()))
				.thenReturn(Optional.ofNullable(null));
		userService.login(userRequestDto);
	}
}
