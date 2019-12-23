package com.scrotify.flexicommerce.repository;


import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.scrotify.flexicommerce.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

	List<Product> getProductsByProductNameContains(String productName);

}
