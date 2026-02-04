package com.dev.ekart.serviceImplementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public Users saveUser(Users userData) {
		Users newUser = userRepo.save(userData);
		return newUser;
	}
	
	
}
