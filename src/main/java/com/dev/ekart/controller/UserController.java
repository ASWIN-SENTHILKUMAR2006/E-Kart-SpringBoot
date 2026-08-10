package com.dev.ekart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.UserRequestDTO;
import com.dev.ekart.responseDTO.UserResponseDTO;
import com.dev.ekart.service.UserService;



/**
 * @author Aswin Senthilkumar
 *
 */

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired 
	UserService userService;
	
	
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<UserResponseDTO>>>getUsers( ){
		List<UserResponseDTO> users =  userService.getUsers();
		ApiResponse<List<UserResponseDTO>> response = new ApiResponse<>("List of Users retrieved", true ,users); 
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody UserRequestDTO UserData){
		
		ApiResponse<UserResponseDTO> response = userService.saveUser(UserData);
		
		if(response.getSuccess() == true) {
			ApiResponse<UserResponseDTO> serviceResponse = new ApiResponse<>(response.getMessage(),true,response.getData());
			return ResponseEntity.ok(serviceResponse);
		}else {
			ApiResponse<UserResponseDTO> serviceResponse = new ApiResponse<>(response.getMessage(),false,null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResponse);
		}
		}
	
	
	
	
	private static final Logger logger = 
	        LoggerFactory.getLogger(UserController.class);

	    @GetMapping("/hello")
	    public String hello() {
	        logger.info("Hello endpoint called");
	        logger.debug("Debugging hello method");
	        logger.warn("This is a warning message");
	        logger.error("This is an error message");

	        return "Hello World";
	    }
	
}
