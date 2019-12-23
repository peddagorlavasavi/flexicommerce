package com.scrotify.flexicommerce.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_order")
@Setter
@Getter
public class UserOrder {
	private Integer orderId;
	private Integer quantity;
	private Double amount;
	private LocalDate orderedDate;
	private Integer userId;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
