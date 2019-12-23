package com.scrotify.flexicommerce.service;

import java.util.List;

import com.scrotify.flexicommerce.entity.Product;

public interface ProductService {

	/**
	 * This method is used for searching the products using productName.
	 * 
	 * @param productName
	 * 
	 * @author Anisha R
	 * 
	 *         This service is used for searching the products.
	 *
	 * @return List<Product>
	 * 
	 *         Here we are listing the products whatever search is done with name.
	 *
	 */

	public List<Product> searchProducts(String productName);

}
