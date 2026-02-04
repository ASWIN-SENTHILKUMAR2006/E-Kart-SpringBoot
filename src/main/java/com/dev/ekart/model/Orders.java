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
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@ToString(exclude = "paymentRel")
@EqualsAndHashCode(exclude="paymentRel")
@EntityListeners(AuditingEntityListener.class)
@Table(name="order_table")
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="order_id")
	Integer orderId;
	
	
	
	@Column(name ="order_status")
	String orderStatus;
	
	@Column(name="total_amount")
	Double totalAmount;
	
	@CreatedDate
	@Column(name="created_at" , nullable=false , updatable =false)
	LocalDateTime createdAt;
	
	//Relationship
	@OneToOne(mappedBy = "orderRel")
	private Payment paymentRel;
	
	@ManyToOne
	@JoinColumn(name="user_id" , referencedColumnName="user_id")
	private Users userRel;
	
	@OneToMany(mappedBy="orderId")
	private List<OrderItem> orderItems;

}
