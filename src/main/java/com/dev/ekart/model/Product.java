package com.dev.ekart.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
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
	
	@Column(name = "name")
	String name ;
	
	@Column(name = "description")
	String description;
	
	@Column(name = "price")
	Double price;
	
	@Column(name = "stock_quantity")
	Integer stockQuantity;
	
	@Column(name = "status" ,  columnDefinition = "integer default '0'")
	Integer status;
	
	@CreatedDate
	@Column(name ="created_at")
	//OffsetDateTime createdAt;
	LocalDateTime createdAt;
	
//	private int sellerId; // used for inital stage implementation before mapping user entity with product entity
	
	
	//RELATIONSHIP
	@OneToMany(mappedBy="product")
	//@JsonManagedReference//used on parent side
	@JsonBackReference("cartItem-product")
	private List<CartItem>  cartItem;
	
	@ManyToOne
	@JoinColumn(name="seller_id" , referencedColumnName="user_id")
	@JsonBackReference("user-product")
	private Users sellerRel;
	
}
