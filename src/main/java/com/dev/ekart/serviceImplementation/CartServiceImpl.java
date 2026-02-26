package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.CartDTO;
import com.dev.ekart.model.Cart;
import com.dev.ekart.model.Users;
import com.dev.ekart.repository.CartRepository;
import com.dev.ekart.repository.UserRepository;
import com.dev.ekart.service.CartService;

import jakarta.transaction.Transactional;

/**
 * @author Aswin Senthilkumar
 *
 */

@Service
public class CartServiceImpl implements CartService {

	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	UserRepository userRepo;
	
	// from Cart End point Creation
	public List<Cart> getCarts(int userId) {
		List<Cart> carts = cartRepo.findByUserRel_UserId(userId);
		
		return carts;
	}
	
	
	@Transactional
	public ApiResponse<Cart> saveCart(CartDTO cartDTO) {
		
		// check if user present
		int userId = cartDTO.getUserId();
		
		Optional <Users> checkUser = userRepo.findById(userId);
		if(checkUser.isEmpty()) {
			ApiResponse<Cart> response = new ApiResponse<>("No such User Id !! , can't create Cart" , false , null);
			return response;
		}
		
		//Optional<Cart> checkpresent =cartRepo.findByUserRel_UserId(UserId);
		
		Optional<Cart> checkpresent =cartRepo.CheckActiveCartExist(userId);

		// If no active Carts only we initialize new Cart and it is ACTIVE
		
		if(checkpresent.isEmpty()) {
			
			Cart cart = new Cart();
			
			Users user = checkUser.get();
			
			cart.setStatus(cartDTO.getStatus());
			cart.setUserRel(user);
			Cart newCart  = cartRepo.save(cart);
			
			ApiResponse<Cart> response = new ApiResponse<>("new Cart Created Successfully" , true , newCart);
			return response; 
		}else {
			ApiResponse<Cart> response = new ApiResponse<>("User Already have an active Cart" , false , null);
			return response;
		}
		
	}
	
	// from other triggers create Cart
	@Transactional
	public ApiResponse<Cart> createCart(int userId){
		
		// check if user present
				
				
				Optional <Users> checkUser = userRepo.findById(userId);
				if(checkUser.isEmpty()) {
					ApiResponse<Cart> response = new ApiResponse<>("No such User Id !! , can't create Cart" , false , null);
					return response;
				}

					Cart cart = new Cart();
					Users user = checkUser.get();
					
					cart.setStatus("ACTIVE");
					cart.setUserRel(user);
					Cart newCart  = cartRepo.save(cart);
					
					ApiResponse<Cart> response = new ApiResponse<>("new Cart Created Successfully - create cart" , true , newCart);
					return response; 

	}
	
	
	
	public ApiResponse<Optional<Cart>> getActiveCarts(int userId){
		Optional<Cart> activeCarts = cartRepo.CheckActiveCartExist(userId);
		if(activeCarts.isPresent()) {
			ApiResponse<Optional<Cart>> response = new ApiResponse<>("User's Active Carts only Retrieved.",true,activeCarts);
			return response;
		}else {
			ApiResponse<Optional<Cart>> response = new ApiResponse<>("User's Does not have any Active Carts.",false,null);
			return response;
		}
	}

}
