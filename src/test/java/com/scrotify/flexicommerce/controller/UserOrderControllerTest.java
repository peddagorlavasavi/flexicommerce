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

import com.scrotify.flexicommerce.dto.UserOrderRequestDto;
import com.scrotify.flexicommerce.dto.UserOrderResponseDto;
import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.service.UserOrderServiceImpl;
import com.scrotify.flexicommerce.utils.ApiConstant;

/**
 * This class is used to do test operation for UserOrderController
 * 
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserOrderControllerTest {
	private static final Logger logger = LoggerFactory.getLogger(UserOrderControllerTest.class);
	@InjectMocks
	UserOrderController userOrderController;
	@Mock
	UserOrderServiceImpl userOrderService;
	UserOrderResponseDto userOrderResponseDto = new UserOrderResponseDto();
	UserOrderRequestDto userOrderRequestDto = new UserOrderRequestDto();
	User user = new User();
	Product product = new Product();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userOrderResponseDto.setStatusCode(ApiConstant.SUCCESS_STATUS_CODE);
		userOrderResponseDto.setStatusMessage(ApiConstant.SUCCESS);
		userOrderResponseDto.setStatusCode(ApiConstant.FAILURE_STATUS_CODE);
		userOrderResponseDto.setStatusMessage(ApiConstant.FAILED);
		user.setUserId(1);
		product.setProductId(1);

	}

	@Test
	public void testBuyProduct() throws CommonException {
		logger.info("Inside buyProductTest method");
		when(userOrderService.buyProduct(1, 1, userOrderRequestDto)).thenReturn(userOrderResponseDto);
		ResponseEntity<UserOrderResponseDto> userOrderResponseDto = userOrderController.buyProduct(1, 1,
				userOrderRequestDto);
		assertEquals(ApiConstant.SUCCESS, userOrderResponseDto.getBody().getStatusMessage());
	}

	@Test
	public void testBuyProductNegative() throws CommonException {
		logger.info("Inside buyProductNegativeTest method");
		when(userOrderService.buyProduct(null, null, userOrderRequestDto)).thenReturn(userOrderResponseDto);
		ResponseEntity<UserOrderResponseDto> userOrderResponseDto = userOrderController.buyProduct(null, null,
				userOrderRequestDto);
		assertEquals(ApiConstant.FAILURE, userOrderResponseDto.getBody().getStatusMessage());
	}
}
