package com.dev.ekart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Cart;
import com.dev.ekart.service.CartService;

/**
 * @author Aswin Senthilkumar
 *
 */

@RestController
@RequestMapping("/cart")
public class CartController {

	
	@Autowired
	CartService cartService ; // here cartservice is an interface using @Autowired spring injects  an instance of CartServiceImplementation in it.
	
	@GetMapping("/list/{userId}")
	public ResponseEntity<ApiResponse<List<Cart>>> getCartList(@PathVariable int userId){
		List<Cart> carts =  cartService.getCarts(userId);
		
		ApiResponse<List<Cart>> response = new ApiResponse<>("List of Carts++" , true , carts);
		return  ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/activecarts/{userId}")
	public ResponseEntity<ApiResponse<Optional<Cart>>> getActiveCartList(@PathVariable int userId){
		
		ApiResponse<Optional<Cart>> response =  cartService.getActiveCarts(userId);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ApiResponse<Cart>> createCart(@RequestBody Cart cart){
		
		ApiResponse<Cart> response = cartService.saveCart(cart);
		 
		return ResponseEntity.ok(response);
	}
	
}
