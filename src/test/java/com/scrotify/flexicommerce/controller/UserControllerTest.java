package com.scrotify.flexicommerce.controller;

import static org.mockito.Mockito.when;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

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

import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.service.UserService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	@Mock
	List<MyOrderResponseDto> orders;

	@Mock
	MyOrderResponseDto myOrderResponseDto;

	@Mock
	UserOrder userOrder;

	@Mock
	List<UserOrder> userOrderList;

	@Mock
	Product product;

	@Mock
	User user;

	int statusCode = 200;

	int statusCodes = 404;

	@Before
	public void setUp() {
		user = new User();
		user.setUserId(1);

		myOrderResponseDto = new MyOrderResponseDto();
		myOrderResponseDto.setAmount(1111D);

		orders = new ArrayList<>();
		orders.add(myOrderResponseDto);

		product = new Product();
		product.setProductId(1);

		userOrder = new UserOrder();
		userOrder.setUser(user);
		userOrder.setProduct(product);
		userOrderList = new ArrayList<>();
		userOrderList.add(userOrder);

	}

	@Test
	public void testMyOrdersPositive() throws CommonException {
		Mockito.when(userService.getOrders(1)).thenReturn(orders);
		ResponseEntity<List<MyOrderResponseDto>> response = userController.searchProducts(1);
		assertEquals(response.getStatusCodeValue(), statusCode);
	}

	@Test
	public void testMyOrdersNegative() throws CommonException {
		Mockito.when(userService.getOrders(1)).thenReturn(orders);
		ResponseEntity<List<MyOrderResponseDto>> response = userController.searchProducts(2);
		assertEquals(response.getStatusCodeValue(), statusCodes);
	}

}
