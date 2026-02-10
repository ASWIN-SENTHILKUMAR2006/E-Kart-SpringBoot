package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Cart;
import com.dev.ekart.repository.CartRepository;
import com.dev.ekart.service.CartService;

/**
 * @author Aswin Senthilkumar
 *
 */

@Service
public class CartServiceImpl implements CartService {

	
	@Autowired
	CartRepository cartRepo;
	
	public List<Cart> getCarts(int userId) {
		List<Cart> carts = cartRepo.findByUserRel_UserId(userId);
		
		return carts;
	}
	
	public ApiResponse<Cart> saveCart(Cart cart) {
		int UserId = cart.getUserRel().getUserId();
		//Optional<Cart> checkpresent =cartRepo.findByUserRel_UserId(UserId);
		Optional<Cart> checkpresent =cartRepo.CheckActiveCartExist(UserId);
		
		if(checkpresent.isEmpty()) {
			Cart newCart  = cartRepo.save(cart);
			ApiResponse<Cart> response = new ApiResponse<>("new Cart Created Successfully" , true , newCart);
			return response;
		}else {
			ApiResponse<Cart> response = new ApiResponse<>("User Already have an active Cart" , false , null);
			return response;
		}
		
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
