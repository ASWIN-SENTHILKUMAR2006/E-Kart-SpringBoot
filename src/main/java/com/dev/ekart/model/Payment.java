package com.dev.ekart.model;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@ToString(exclude = "orderRel")
@EqualsAndHashCode(exclude = "orderRel")

@Table(name="payment_table")
public class Payment {
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	@Column(name="payment_id")
	Integer paymentId;
	

	
	@Column(name ="payment_mode")
	String paymentMode;
	
	@Column(name ="payment_status")
	String paymentStatus;
	

	@Column(name="transaction_ref")
	String transactionRef;
	
	
	// Relationship
	@OneToOne
	@JoinColumn(name="order_id", referencedColumnName="order_id")
	private Orders orderRel;
}
