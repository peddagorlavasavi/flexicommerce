package com.scrotify.flexicommerce.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.service.UserOrderService;
import com.scrotify.flexicommerce.service.UserOrderServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class MyOrderControllerTest {

	@InjectMocks
	UserOrderController userOrderController;

	@Mock
	UserOrderService userOrderService;

	@InjectMocks
	UserOrderServiceImpl userOrderServiceImpl;

	@Mock
	UserOrderRepository userOrderRepository;

	@Mock
	UserRepository userRepository;

	List<MyOrderResponseDto> orders;

	MyOrderResponseDto myOrderResponseDto;

	UserOrder userOrder;

	List<UserOrder> userOrders;

	Product product;

	User user;

	int one = 1;

	int statusCode = 200;

	int statusCodes = 404;

	@Before
	public void setUp() {
		user = new User();
		user.setUserId(1);

		myOrderResponseDto = new MyOrderResponseDto();
		myOrderResponseDto.setProductName("aaaa");
		myOrderResponseDto.setAmount(1111D);

		orders = new ArrayList<>();
		orders.add(myOrderResponseDto);

		product = new Product();
		product.setProductId(1);

		userOrder = new UserOrder();
		userOrder.setUser(user);
		userOrder.setOrderedDate(LocalDate.parse("1111-12-11"));
		userOrder.setOrderId(1);
		userOrder.setProduct(product);
		userOrder.setQuantity(10);
		userOrder.setUser(user);

		userOrders = new ArrayList<>();
		userOrders.add(userOrder);

	}

	@Test
	public void testMyOrdersPositive() throws CommonException {
		Mockito.when(userOrderService.getMyOrders(1)).thenReturn(orders);
		ResponseEntity<List<MyOrderResponseDto>> response = userOrderController.myOrders(1);
		assertEquals(response.getStatusCodeValue(), statusCode);
	}

	@Test
	public void testMyOrdersNegative() throws CommonException {
		Mockito.when(userOrderService.getMyOrders(1)).thenReturn(orders);
		ResponseEntity<List<MyOrderResponseDto>> response = userOrderController.myOrders(3);
		assertEquals(response.getStatusCodeValue(), statusCodes);
	}

}
