package com.dev.ekart.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data 
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CartItem {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_item_id")
	Integer cartItemId;
	
	@Column(name = "cart_id")
	Integer cartId;
	
	@Column(name="product_id")
	Integer productId;
	
	@Column(name="quantity")
	Integer Quantity;
	
	@Column(name="price_at_add_time")
	Double priceAtAddTime;
	
	
	// RELATIONSHIPS
	
	
	
	
}
