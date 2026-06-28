package com.dev.ekart.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for adding an item to the active cart.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemRequestDTO {

	private Integer productId;
	private Integer quantity;
	private Integer userId;
}
