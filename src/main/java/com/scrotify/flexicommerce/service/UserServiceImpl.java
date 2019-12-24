package com.scrotify.flexicommerce.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.flexicommerce.dto.UserRequestDto;
import com.scrotify.flexicommerce.dto.UserResponseDto;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.exception.UserNotFoundException;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.utils.StringConstant;

/**
 * @author Vasavi
 * @since 2019-12-23 This class is used for logging in to the application
 * 
 */
@Service
public class UserServiceImpl implements UserService {

	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * This will inject all the implementations of the userOrderRepository.
	 */

	@Autowired
	UserOrderRepository userOrderRepository;

	@Autowired
	UserRepository userRepository;

	/**
	 * This method is used for logging into the application.
	 * 
	 * @param userRequestDto the userRequestDto which contains userName and
	 *                       password.
	 * @return userResponseDto.
	 * @throws UserNotFoundException
	 */
	@Override
	public UserResponseDto login(UserRequestDto userRequestDto) throws UserNotFoundException {
		logger.debug("Inside  UserServiceImpl :login method");
		UserResponseDto userResponseDto = new UserResponseDto();
		Optional<User> user = userRepository.findByUserNameAndPassword(userRequestDto.getUserName(),
				userRequestDto.getPassword());
		if (!user.isPresent()) {
			throw new UserNotFoundException(StringConstant.FAILURE);
		}
		userResponseDto.setUserId(user.get().getUserId());
		userResponseDto.setUserName(user.get().getUserName());
		userResponseDto.setMessage(StringConstant.SUCCESS);
		userResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		return userResponseDto;

	}
}