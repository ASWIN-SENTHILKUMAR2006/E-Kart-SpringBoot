package com.dev.ekart.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.CartRequestDTO;
import com.dev.ekart.responseDTO.CartResponseDTO;
import com.dev.ekart.model.Cart;
import com.dev.ekart.model.CartItem;
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
	public List<CartResponseDTO> getCarts(int userId) {
		return cartRepo.findByUserRel_UserId(userId).stream().map(this::toResponseDTO).collect(Collectors.toList());
	}
	
	
	@Transactional
	public ApiResponse<CartResponseDTO> saveCart(CartRequestDTO cartDTO) {
		
		// check if user present
		int userId = cartDTO.getUserId();
		
		Optional <Users> checkUser = userRepo.findById(userId);
		if(checkUser.isEmpty()) {
			ApiResponse<CartResponseDTO> response = new ApiResponse<>("No such User Id !! , can't create Cart" , false , null);
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
			
			ApiResponse<CartResponseDTO> response = new ApiResponse<>("new Cart Created Successfully" , true , toResponseDTO(newCart));
			return response; 
		}else {
			ApiResponse<CartResponseDTO> response = new ApiResponse<>("User Already have an active Cart" , false , null);
			return response;
		}
		
	}
	
	// from other triggers create Cart
	@Transactional
	public ApiResponse<CartResponseDTO> createCart(int userId){
		
		// check if user present
				
				
				Optional <Users> checkUser = userRepo.findById(userId);
				if(checkUser.isEmpty()) {
					ApiResponse<CartResponseDTO> response = new ApiResponse<>("No such User Id !! , can't create Cart" , false , null);
					return response;
				}

					Cart cart = new Cart();
					Users user = checkUser.get();
					
					cart.setStatus("ACTIVE");
					cart.setUserRel(user);
					Cart newCart  = cartRepo.save(cart);
					
					ApiResponse<CartResponseDTO> response = new ApiResponse<>("new Cart Created Successfully - create cart" , true , toResponseDTO(newCart));
					return response; 

	}
	
	
	
	public ApiResponse<Optional<CartResponseDTO>> getActiveCarts(int userId){
		Optional<Cart> activeCarts = cartRepo.CheckActiveCartExist(userId);
		if(activeCarts.isPresent()) {
			ApiResponse<Optional<CartResponseDTO>> response = new ApiResponse<>("User's Active Carts only Retrieved.",true,Optional.of(toResponseDTO(activeCarts.get())));
			return response;
		}else {
			ApiResponse<Optional<CartResponseDTO>> response = new ApiResponse<>("User's Does not have any Active Carts.",false,null);
			return response;
		}
	}
	
	
	public ApiResponse<String>  checkoutCartItems(int userId) {
		Optional<Cart> activeCart = cartRepo.CheckActiveCartExist(userId);
		if(activeCart.isEmpty()) {
			ApiResponse<String> response = new ApiResponse<>("User's Does not have any Active Carts.",false,null);
			return response;			
		}
		Cart data = activeCart.get();
		List<CartItem> cartItems = data.getCartItem();
		// TODO: checkout flow is not implemented in the original codebase; returning a placeholder keeps the DTO refactor limited to the coded path.
		return new ApiResponse<>("Checkout flow is not implemented yet.", false, null);
		
	}

	private CartResponseDTO toResponseDTO(Cart cart) {
		if (cart == null) {
			return null;
		}
		return new CartResponseDTO(
				cart.getCartId(),
				cart.getStatus(),
				cart.getCreatedAt(),
				cart.getUserRel() != null ? cart.getUserRel().getUserId() : null);
	}

}
