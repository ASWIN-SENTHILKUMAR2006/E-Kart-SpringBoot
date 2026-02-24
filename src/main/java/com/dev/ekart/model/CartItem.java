package com.dev.ekart.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	Integer quantity = 1;
	
	@Column(name="price_at_buy_time")
	Double priceAtBuyTime; // unwanted field
	
	
	// RELATIONSHIPS
	@ManyToOne
	@JoinColumn(name="product_id" , referencedColumnName="product_id")
	@JsonBackReference
	private Product product;
	
	@ManyToOne
	@JoinColumn(name="cart_id", referencedColumnName="cart_id")
	@JsonBackReference
	private Cart cart;
	
	
}
