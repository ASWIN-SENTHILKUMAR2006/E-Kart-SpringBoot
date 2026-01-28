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
public class OrderItem {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="order_item_id")
	Integer orderItemId;
	

	
	@Column(name ="product_name")
	String  productName;
	
	@Column(name="price_at_purchase")
	Double priceAtPurchase;
	
	@Column(name = "quantity")
	Integer quantity;
	
	@Column(name="seller_id")
	Integer sellerId;
	
	//RELATIONSHIP
	@ManyToOne
	@JoinColumn(name="order_id", referencedColumnName="order_id")
	private Orders orderId;
	
}
