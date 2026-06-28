package com.dev.ekart.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating a cart.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {

	private Integer userId;
	private String status;
}
