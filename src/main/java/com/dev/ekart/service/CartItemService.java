package com.dev.ekart.service;

import java.util.List;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.CartItemRequestDTO;
import com.dev.ekart.responseDTO.CartItemResponseDTO;

/**
 * @author Aswin Senthilkumar
 *
 */


public interface CartItemService  {

	
	public ApiResponse<CartItemResponseDTO> addCartItem(CartItemRequestDTO cartitem);

	/**
	 * @return
	 */
	public ApiResponse<List<CartItemResponseDTO>> getActiveCartItemsDetails(int userId);
	
}
