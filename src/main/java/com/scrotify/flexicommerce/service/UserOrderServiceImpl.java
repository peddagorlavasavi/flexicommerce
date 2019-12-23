package com.scrotify.flexicommerce.service;

import java.time.LocalDate;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
 * This class is used to buy a product
 * 
 * @author Vasavi
 * @since 2019-12-23
 *
 */
@Service
public class UserOrderServiceImpl implements UserOrderService {
	/**
	 * The Constant log.
	 */
	private static final Logger logger = LoggerFactory.getLogger(UserOrderServiceImpl.class);
	@Autowired
	UserOrderRepository userOrderRepository;
	@Autowired
	ProductRepository productRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired(required = true)
	RestTemplate restTemplate;

	/**
	 * This method is used to buy a product based on userId and productId
	 * 
	 * @param userId
	 * @param productId
	 * @param userOrderRequestDto which contains
	 *                            creditCardAccountNumber,CVV,pin,exipryDate and
	 *                            quantity.
	 * @return userOrderResponseDto
	 */
	@Override
	public UserOrderResponseDto buyProduct(Integer userId, Integer productId, UserOrderRequestDto userOrderRequestDto) {
		logger.info("Inside UserOrderServiceImpl : buyProduct");
		UserOrderResponseDto userOrderResponseDto = new UserOrderResponseDto();
		UserOrder userOrder = new UserOrder();
		Optional<Product> product = productRepository.findById(productId);
		Optional<User> user = userRepository.findById(userId);
		FundTransferRequestDto fundTransferRequestDto = new FundTransferRequestDto();
		BeanUtils.copyProperties(userOrderRequestDto, fundTransferRequestDto);
		if (user.isPresent() && product.isPresent()) {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<FundTransferRequestDto> entity = new HttpEntity<>(fundTransferRequestDto, headers);
			String url = "http://10.117.189.169:8089/flexibanking/transactions/fund-transfer";
			ResponseEntity<Integer> statusCode = restTemplate.postForEntity(url, entity, Integer.class);
			if (statusCode.getStatusCodeValue() == 200) {
				userOrder.setAmount(product.get().getUnitPrice() * userOrderRequestDto.getQuantity());
				userOrder.setOrderId(userOrder.getOrderId());
				userOrder.setProduct(product.get());
				userOrder.setUser(user.get());
				userOrder.setOrderedDate(LocalDate.now());
				userOrder.setQuantity(userOrderRequestDto.getQuantity());
				userOrderResponseDto.setStatusMessage(StringConstant.PRODUCT_PURCHASED);
				userOrderResponseDto.setStatusCode(StringConstant.SUCCESS_STATUS_CODE);
				userOrderRepository.save(userOrder);
			} else {
				throw new CommonException(StringConstant.TRANSACTION_FAILED);
			}

		} else {
			userOrderResponseDto.setStatusMessage(StringConstant.FAILURE_PRODUCT);
			userOrderResponseDto.setStatusCode(StringConstant.FAILURE_STATUS_CODE);
		}

		return userOrderResponseDto;

	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
