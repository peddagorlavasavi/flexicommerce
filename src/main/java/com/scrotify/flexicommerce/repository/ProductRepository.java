package com.scrotify.flexicommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexicommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

}
