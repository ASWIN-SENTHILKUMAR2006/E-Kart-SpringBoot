package com.dev.ekart.service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.responseDTO.OrderResponseDTO;

/**
 * @author Aswin Senthilkumar
 *
 */
public interface OrderService {

	/**
	 * @param userId
	 * @return
	 */
	ApiResponse<OrderResponseDTO> checkout(int userId);

}
