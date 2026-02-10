package com.dev.ekart.service;

import java.util.List;
import java.util.Optional;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Cart;

/**
 * @author Aswin Senthilkumar
 *
 */

public interface CartService {

	public List<Cart> getCarts(int userId);

	/**
	 * @param cart
	 * @return
	 */
	public ApiResponse<Cart> saveCart(Cart cart);

	/**
	 * @param userId
	 * @return
	 */
	public ApiResponse<Optional<Cart>> getActiveCarts(int userId);
	

}
