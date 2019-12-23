package com.scrotify.flexicommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@CrossOrigin
@Slf4j
public class ProductController {

	/**
	 * These are the implementations of product Service.
	 */

	@Autowired
	ProductService productService;

	/**
	 * 
	 * @author Anisha R
	 * 
	 *         The productName is given and searching the products as a list.
	 * 
	 * @param productName
	 * 
	 *                    The productName is searched here is done by partialSearch.
	 * 
	 * @return List<Product>
	 * 
	 *         Here we are listing the list of products available while searching
	 *         the products.
	 * 
	 */

	@GetMapping("/{productName}")
	public ResponseEntity<List<Product>> searchProducts(@PathVariable("productName") String productName) {
		log.info("Get list of product details");
		List<Product> productList = productService.searchProducts(productName);
		return new ResponseEntity<>(productList, HttpStatus.OK);
	}
}
