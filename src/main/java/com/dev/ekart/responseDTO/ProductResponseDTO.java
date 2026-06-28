package com.dev.ekart.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for product details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO {

	private Integer productId;
	private String name;
	private String description;
	private Double price;
	private Integer stockQuantity;
	private Integer status;
	private LocalDateTime createdAt;
	private Integer sellerId;
}
