package com.dev.ekart.model;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", updatable = false)
	Integer userId;
	
	@Column(name="name")
	String name;
	
	@Column(name="email" , unique = true)
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="role")
	String role;
	
	@Column(name="status")
	String status;
	
	@CreatedDate
	@Column(name="created_at")
	LocalDateTime createdAt;
	
	
	// RELATIONSHIPS
//	@OneToOne
//	@JoinColumn(name="cart_id" , referencedColumnName = "cart_id")
//	private Cart cart; 

	@OneToMany(mappedBy="userRel")
	private List<Orders> orderRel;
	
	@OneToMany(mappedBy = "userRel")
	private List<Address> addresses;
	
	@OneToMany(mappedBy="sellerRel")
	@JsonManagedReference
	private List<Product> products;
	
	@OneToMany(mappedBy="userRel")
	@JsonManagedReference
	List<Cart> carts;

}
