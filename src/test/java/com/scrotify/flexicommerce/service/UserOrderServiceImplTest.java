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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.scrotify.flexicommerce.dto.FundTransferRequestDto;
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
	@Mock
	RestTemplate restTemplate;

	UserOrderResponseDto userOrderResponseDto = new UserOrderResponseDto();
	UserOrderRequestDto userOrderRequestDto = new UserOrderRequestDto();
	FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
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
		userOrderRequestDto.setCreditCardNumber(123456L);
		userOrderRequestDto.setCvv(123);
		userOrderRequestDto.setExpiryDate(LocalDate.now());
		userOrderRequestDto.setPin(1234);
		userOrderRequestDto.setQuantity(1);
		userOrderRequestDto.setToAccount(345678L);
		userOrderRequestDto.setTransactionAmount(15000.00);
		userOrderRequestDto.setTransactionDescription("Mobile");
		product.setProductId(1);
		userOrder.setAmount(20000.00);
		userOrder.setOrderedDate(LocalDate.now());
		userOrder.setOrderId(1);
		userOrder.setQuantity(1);

	}

	@Test
	public void testBuyProduct() throws CommonException {
		logger.info("Inside buyProductTest method");
		Optional<Product> optionalProduct = Optional.of(product);
		Optional<User> optionalUser = Optional.of(user);
		when(productRepository.findById(1)).thenReturn(optionalProduct);
		when(userRepository.findById(1)).thenReturn(optionalUser);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<FundTransferRequestDto> entity = new HttpEntity<>(fundTransferRequestDto, headers);
		String url = "http://10.117.189.169:8089/flexibanking/transactions/fund-transfer";
		ResponseEntity<Integer> response = ResponseEntity.accepted().body(200);
		when(restTemplate.postForEntity(url, entity, Integer.class)).thenReturn(response);
		when(userOrderRepository.save(userOrder)).thenReturn(userOrder);
		UserOrderResponseDto userOrderResponseDto = userOrderService.buyProduct(1, 1, userOrderRequestDto);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(StringConstant.SUCCESS_STATUS_CODE, userOrderResponseDto.getStatusCode());

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
