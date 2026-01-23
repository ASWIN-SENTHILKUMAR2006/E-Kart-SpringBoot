package com.dev.ekart.model;

import org.springframework.data.annotation.CreatedDate;
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
import java.time.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="order_id")
	Integer orderId;
	
	@Column(name="customer_id")
	Integer customerId;
	
	@Column(name ="order_status")
	String orderStatus;
	
	@Column(name="total_amount")
	Double totalAmount;
	
	@CreatedDate
	@Column(name="created_at" , nullable=false , updatable =false)
	OffsetDateTime createdAt;
	
	//Relationship
	
	

}
