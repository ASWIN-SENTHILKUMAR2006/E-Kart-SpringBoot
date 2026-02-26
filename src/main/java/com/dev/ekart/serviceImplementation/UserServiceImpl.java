package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Users;
import com.dev.ekart.repository.UserRepository;
import com.dev.ekart.service.UserService;

/**
 * @author Aswin Senthilkumar
 *
 */
@Service
public class UserServiceImpl  implements UserService{

	@Autowired
	UserRepository userRepo;
	
	public List<Users> getUsers(){
		List<Users> users = userRepo.findAll();
		return users;
	}
	
	public ApiResponse<Users> saveUser(Users userData) {
		// check if user already exist
		String email = userData.getEmail();
		Optional<Users> checkUserExist = userRepo.findByEmail(email);
		
		if(checkUserExist.isPresent()) {
			ApiResponse<Users> response =  new ApiResponse<>("User Already Exist , email match Found !!",false , null);
			return response;
		}
		
		Users newUser = userRepo.save(userData);
		ApiResponse<Users> response = new ApiResponse<>("User Created Successfully" , true , newUser);
		
		return response;
	}
	
	
}
