package com.scrotify.flexicommerce.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
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

import com.scrotify.flexicommerce.dto.UserOrderRequestDto;
import com.scrotify.flexicommerce.dto.UserOrderResponseDto;
import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.repository.ProductRepository;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.utils.StringConstant;

/**
 * This class is used to do test operation for UserOrderServiceImpl c
 * 
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class UserOrderServiceImplTest {
	private static final Logger logger = LoggerFactory.getLogger(UserOrderServiceImplTest.class);
	@InjectMocks
	UserOrderServiceImpl userOrderService;
	@Mock
	UserOrderRepository userOrderRepository;
	@Mock
	ProductRepository productRepository;
	@Mock
	UserRepository userRepository;
	UserOrderResponseDto userOrderResponseDto = new UserOrderResponseDto();
	UserOrderRequestDto userOrderRequestDto = new UserOrderRequestDto();
	Product product = new Product();
	User user = new User();
	UserOrder userOrder = new UserOrder();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		userOrderResponseDto.setStatusMessage(StringConstant.PRODUCT_PURCHASED);
		userOrderResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
		userOrderResponseDto.setStatusMessage(StringConstant.TRANSACTION_FAILED);
		userOrderResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
		product.setProductId(1);
		userOrder.setAmount(20000.00);
		userOrder.setOrderedDate(LocalDate.now());
		userOrder.setOrderId(1);
		userOrder.setQuantity(1);

	}

	@Test
	public void testBuyProductNegativeTest() throws CommonException {
		logger.info("Inside buyProductNegativetTest method");
		when(productRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		when(userRepository.findById(1)).thenReturn(Optional.ofNullable(null));
		UserOrderResponseDto userOrderResponseDto = userOrderService.buyProduct(1, 1, userOrderRequestDto);
		assertEquals(StringConstant.FAILURE_STATUS_CODE, userOrderResponseDto.getStatusCode());

	}
}
