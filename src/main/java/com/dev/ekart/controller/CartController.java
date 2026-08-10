package com.dev.ekart.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.CartRequestDTO;
import com.dev.ekart.responseDTO.CartResponseDTO;
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
	public ResponseEntity<ApiResponse<List<CartResponseDTO>>> getCartList(@PathVariable int userId){
		List<CartResponseDTO> carts =  cartService.getCarts(userId);
		
		ApiResponse<List<CartResponseDTO>> response = new ApiResponse<>("List of Carts++" , true , carts);
		return  ResponseEntity.ok(response);
	}
	
	
	@GetMapping("/activecarts/{userId}")
	public ResponseEntity<ApiResponse<Optional<CartResponseDTO>>> getActiveCartList(@PathVariable int userId){

		ApiResponse<Optional<CartResponseDTO>> response =  cartService.getActiveCarts(userId);
		if(response.getSuccess() == false) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
		}
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/create") // try - ApiResponse<T>
	public ResponseEntity<ApiResponse<CartResponseDTO>> createCart(@RequestBody CartRequestDTO cart){

		ApiResponse<CartResponseDTO> response = cartService.saveCart(cart);

		if(response.getSuccess() == true) {
		ApiResponse<CartResponseDTO> serviceResponse = new ApiResponse<>(response.getMessage(), true , response.getData());
		return ResponseEntity.ok(serviceResponse);
		}else {
			ApiResponse<CartResponseDTO> serviceResponse = new ApiResponse<>(response.getMessage() , false , null);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(serviceResponse);
		}

	}

}
