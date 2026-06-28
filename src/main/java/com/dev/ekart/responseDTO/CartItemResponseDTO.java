package com.dev.ekart.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for cart item details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemResponseDTO {

	private Integer cartItemId;
	private Integer quantity;
	private Double priceAtBuyTime;
	private Integer productId;
	private String productName;
	private Integer cartId;
}
