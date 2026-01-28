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
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer addressId;
	
	@Column(name = "customer_id")
	Integer customerId;
	
	@Column(name="street_details")
	String street;
	
	@Column(name="city")
	String city;
	
	@Column(name="state")
	String state;
	
	@Column(name="pincode")
	String pincode;
	
	@Column(name="is_default")
	Boolean isDefault;
	
	//RELATIONSHIP
	@ManyToOne
	@JoinColumn(name="user_id" , referencedColumnName = "user_id ")
	private Users user_rel;
	
}
