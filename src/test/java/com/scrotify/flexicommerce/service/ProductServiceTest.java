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

import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.repository.ProductRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductServiceTest {

	@InjectMocks
	ProductServiceImpl productServiceImpl;

	@Mock
	ProductRepository productRepository;

	@Mock
	List<Product> products;

	@Mock
	Product product;

	int one = 1;

	@Before
	public void setUp() {
		product = new Product();
		product.setDescription("aaa");
		product.setProductName("aaaa");
		product.setImageUrl("aaa");
		product.setUnitPrice(11111D);
		product.setProductId(1);
		products = new ArrayList<>();
		products.add(product);
	}

	@Test
	public void testSearchProductsPositive() {
		Mockito.when(productRepository.getProductsByProductNameContains("aaaa")).thenReturn(products);
		List<Product> response = productServiceImpl.searchProducts("aaaa");
		assertEquals(response.size(), one);
	}

}
