package com.dev.ekart.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for cart details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

	private Integer cartId;
	private String status;
	private LocalDateTime createdAt;
	private Integer userId;
}
