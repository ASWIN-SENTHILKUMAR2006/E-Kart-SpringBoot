package com.dev.ekart.dto;

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
public class CartItemDTO {

	
	Integer productId;
	Integer quantity;
	//Double  priceAtBuyTime;
	Integer userId;
	
	
}
