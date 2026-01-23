package com.dev.ekart.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="payment_id")
	Integer paymentId;
	
	@Column(name = "order_id")
	Integer orderId;
	
	@Column(name ="payment_mode")
	String paymentMode;
	
	@Column(name ="payment_status")
	String paymentStatus;
	
	@Transient
	@Column(name="transaction_ref")
	String transactionRef;
	
	
	// Relationship

}
