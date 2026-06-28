package com.dev.ekart.service;

import java.util.List;
import java.util.Optional;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.CartRequestDTO;
import com.dev.ekart.responseDTO.CartResponseDTO;

/**
 * @author Aswin Senthilkumar
 *
 */

public interface CartService {

	public List<CartResponseDTO> getCarts(int userId);

	/**
	 * @param cart
	 * @return
	 */
	public ApiResponse<CartResponseDTO> saveCart(CartRequestDTO cart);

	/**
	 * @param userId
	 * @return
	 */
	public ApiResponse<Optional<CartResponseDTO>> getActiveCarts(int userId);
	
	/**
	 * @param userId
	 * @return
	 */
	public ApiResponse<CartResponseDTO> createCart(int userId);

	/**
	 * @param userId
	 * @return
	 */
	public ApiResponse<String>  checkoutCartItems(int userId);
}


