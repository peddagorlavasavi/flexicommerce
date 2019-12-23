package com.scrotify.flexicommerce.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.service.ProductService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProductControllerTest {
	
	@InjectMocks
	ProductController productController;
	
	@Mock
	ProductService productService;
	
    List<Product> products;
	
	Product product;
	
	int code =200;
	
	@Before(value = "")
	public void setUp() {
		Product product = new Product();
		product.setDescription("aaa");
		product.setProductName("aaaa");
		product.setImageUrl("aaa");
		product.setUnitPrice(11111D);
		product.setProductId(1);
		products=new ArrayList<>();
		products.add(product);		
	}
	
	
	@Test
	public void testSearchProductsPositive() {
		Mockito.when(productService.searchProducts("aaa")).thenReturn(products);
		ResponseEntity<List<Product>> response = productController.searchProducts("aaa");
		assertEquals(response.getStatusCode().value(),code);
	}
	

}
