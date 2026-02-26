package com.dev.ekart.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aswin Senthilkumar
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {

	String status;
	LocalDateTime createdAt;
	Integer userId;
}
