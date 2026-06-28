package com.dev.ekart.service;

import java.util.List;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.UserRequestDTO;
import com.dev.ekart.responseDTO.UserResponseDTO;

/**
 * @author Aswin Senthilkumar
 *
 */
public interface UserService {
	
	public List<UserResponseDTO> getUsers() ;

	/**
	 * @param user
	 * @return
	 */
	public ApiResponse<UserResponseDTO> saveUser(UserRequestDTO userData);
	
}
