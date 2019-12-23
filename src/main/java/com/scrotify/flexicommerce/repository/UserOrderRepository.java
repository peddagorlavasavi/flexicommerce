package com.scrotify.flexicommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexicommerce.entity.UserOrder;

@Repository
public interface UserOrderRepository extends JpaRepository<UserOrder, Integer> {
	List<UserOrder> findByUserUserId(Integer userId);
	public UserOrder findByUserAndProduct(Integer userId, Integer productId);

}
