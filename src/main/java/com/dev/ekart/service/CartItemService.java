package com.dev.ekart.service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.CartItemDTO;
import com.dev.ekart.model.CartItem;

/**
 * @author Aswin Senthilkumar
 *
 */


public interface CartItemService  {

	
	public ApiResponse<CartItem> addCartItem(CartItemDTO cartitem);
	
}
