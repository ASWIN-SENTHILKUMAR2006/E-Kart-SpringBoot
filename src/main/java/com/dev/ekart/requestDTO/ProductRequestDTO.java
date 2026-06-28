package com.dev.ekart.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating or updating a product.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

	private String name;
	private String description;
	private Double price;
	private Integer stockQuantity;
}
