package com.scrotify.flexicommerce.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.scrotify.flexicommerce.dto.MyOrderResponseDto;
import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.entity.User;
import com.scrotify.flexicommerce.entity.UserOrder;
import com.scrotify.flexicommerce.exception.CommonException;
import com.scrotify.flexicommerce.repository.UserOrderRepository;
import com.scrotify.flexicommerce.repository.UserRepository;
import com.scrotify.flexicommerce.utils.ApiConstant;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

	@InjectMocks
	UserServiceImpl UserServiceImpl;

	@Mock
	UserRepository userRepository;

	@Mock
	UserOrderRepository userOrderRepository;

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

	int one = 1;

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
		Mockito.when(userOrderRepository.findByUserUserId(1)).thenReturn(userOrderList);
		List<MyOrderResponseDto> response = UserServiceImpl.getOrders(1);
		assertEquals(response.size(), one);
	}

	@Test(expected = CommonException.class)
	public void testMyOrdersNegative() throws CommonException {
		Mockito.when(userOrderRepository.findByUserUserId(1)).thenReturn(userOrderList);
		List<MyOrderResponseDto> response = UserServiceImpl.getOrders(2);
		assertEquals(ApiConstant.USERID_NOT_FOUND_MESSAGE, response);
	}

}
