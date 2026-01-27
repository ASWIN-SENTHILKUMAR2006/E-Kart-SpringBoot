package com.dev.ekart.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cart_id")
	Integer cartId;
	
	@Column(name="customer_id")
	Integer customerId;
	
	@Column(name="status")
	String status;
	
	@CreatedDate
	@Column(name ="created_at")
	LocalDateTime createdAt;
	
	// RELATIONSHIPS
	@OneToMany(mappedBy="cart")
	private List<CartItem>  cartItem;
	
	@OneToOne(mappedBy="cart")
	private Users user;
	
}
