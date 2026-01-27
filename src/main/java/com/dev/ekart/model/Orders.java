package com.dev.ekart.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author Aswin Senthilkumar
 *
 */

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "payment_rel")
@EqualsAndHashCode(exclude="payment_rel")
@EntityListeners(AuditingEntityListener.class)
@Table(name="order_table")
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
	LocalDateTime createdAt;
	
	//Relationship
	@OneToOne(mappedBy = "order_rel")
	private Payment payment_rel;

}
