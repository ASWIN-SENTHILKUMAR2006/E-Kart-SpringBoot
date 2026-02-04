package com.dev.ekart.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aswin Senthilkumar
 *
 */

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
	
	@Column(name="quantity")
	Integer quantity;
	
	@Column(name="price_at_add_time")
	Double priceAtAddTime;
	
	
	// RELATIONSHIPS
	@ManyToOne
	@JoinColumn(name="product_id" , referencedColumnName="product_id")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cart_id", referencedColumnName="cart_id")
	private Cart cart;
	
	
}
