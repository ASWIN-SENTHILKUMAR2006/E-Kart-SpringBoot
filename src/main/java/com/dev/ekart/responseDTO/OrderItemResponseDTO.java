package com.dev.ekart.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for order item details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemResponseDTO {

	private Integer orderItemId;
	private String productName;
	private Double priceAtPurchase;
	private Integer quantity;
	private Integer sellerId;
}
