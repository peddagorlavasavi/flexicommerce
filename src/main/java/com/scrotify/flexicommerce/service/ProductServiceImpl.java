package com.scrotify.flexicommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scrotify.flexicommerce.entity.Product;
import com.scrotify.flexicommerce.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	/**
	 * This will inject all the implementations of the productRepository.
	 */

	@Autowired
	ProductRepository productRepository;

	/**
	 * This API is used to search the products in productList
	 * 
	 * @param productName
	 *
	 * @return List<Product>
	 * 
	 *         This returns the list of products of by search of partial
	 *         productName.
	 * 
	 * @author Anisha R
	 * 
	 */

	@Override
	public List<Product> searchProducts(String productName) {
		log.info("Entering into search Products");
		List<Product> products = productRepository.getProductsByProductNameContains(productName);
		return products;
	}

}
