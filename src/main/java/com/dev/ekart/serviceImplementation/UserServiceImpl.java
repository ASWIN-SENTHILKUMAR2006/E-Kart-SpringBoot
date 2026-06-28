package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.UserRequestDTO;
import com.dev.ekart.responseDTO.UserResponseDTO;
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
	
	public List<UserResponseDTO> getUsers(){
		return userRepo.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
	}
	
	public ApiResponse<UserResponseDTO> saveUser(UserRequestDTO userData) {
		// check if user already exist
		String email = userData.getEmail();
		Optional<Users> checkUserExist = userRepo.findByEmail(email);
		
		if(checkUserExist.isPresent()) {
			ApiResponse<UserResponseDTO> response =  new ApiResponse<>("User Already Exist , email match Found !!",false , null);
			return response;
		}
		
		Users newUser = new Users();
		newUser.setName(userData.getName());
		newUser.setEmail(userData.getEmail());
		newUser.setPassword(userData.getPassword());
		newUser.setRole(userData.getRole());
		newUser.setStatus(userData.getStatus());
		Users savedUser = userRepo.save(newUser);
		ApiResponse<UserResponseDTO> response = new ApiResponse<>("User Created Successfully" , true , toResponseDTO(savedUser));
		
		return response;
	}

	private UserResponseDTO toResponseDTO(Users user) {
		if (user == null) {
			return null;
		}
		return new UserResponseDTO(
				user.getUserId(),
				user.getName(),
				user.getEmail(),
				user.getRole(),
				user.getStatus(),
				user.getCreatedAt());
	}
	
	
}
