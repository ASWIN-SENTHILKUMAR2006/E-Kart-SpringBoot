package com.dev.ekart.service;

import java.util.List;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Users;

/**
 * @author Aswin Senthilkumar
 *
 */
public interface UserService {
	
	public List<Users> getUsers() ;

	/**
	 * @param user
	 * @return
	 */
	public ApiResponse<Users> saveUser(Users userData);
	
}
