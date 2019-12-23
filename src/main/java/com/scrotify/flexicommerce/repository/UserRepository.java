package com.scrotify.flexicommerce.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scrotify.flexicommerce.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByUserNameAndPassword(String userName, String password);
}
