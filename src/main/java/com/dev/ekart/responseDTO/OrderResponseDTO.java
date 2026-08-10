package com.dev.ekart.responseDTO;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for order details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

	private Integer orderId;
	private String orderStatus;
	private Double totalAmount;
	private LocalDateTime createdAt;
	private Integer userId;
	private List<OrderItemResponseDTO> orderItems;
}
