package com.dev.ekart.model;

import java.time.OffsetDateTime;
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
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	Integer productId;
	
	@Column(name = " name ")
	String name ;
	
	@Column(name = "description")
	String description;
	
	@Column(name = "price")
	Double price;
	
	@Column(name = "stock_quantity")
	Integer stockQuantity;
	
	@Column(name = "status")
	String status;
	
	@CreatedDate
	@Column(name ="created_at")
	OffsetDateTime createdAt;
	
	
	@Column(name="seller_id")
	Integer sellerId;
	
	//RELATIONSHIP
	@OneToMany(mappedBy="product")
	private List<CartItem>  cartItem;
	
}
