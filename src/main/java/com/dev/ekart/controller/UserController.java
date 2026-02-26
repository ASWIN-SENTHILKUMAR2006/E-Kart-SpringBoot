package com.dev.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Users;
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
	public ResponseEntity<ApiResponse<List<Users>>>getUsers( ){
		List<Users> users =  userService.getUsers();
		ApiResponse<List<Users>> response = new ApiResponse<>("List of Users retrieved", true ,users); 
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Users>> createUser(@RequestBody Users UserData){
		Users user = userService.saveUser(UserData);
		ApiResponse<Users> response = new ApiResponse<>("User Created Successfully", true , user);
		return ResponseEntity.ok(response);
	}
	
}
