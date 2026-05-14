package com.dev.ekart.controller;

import java.util.List;

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
import com.dev.ekart.dto.CartItemDTO;
import com.dev.ekart.model.CartItem;
import com.dev.ekart.service.CartItemService;

/**
 * @author Aswin Senthilkumar
 *
 */

@RestController
@RequestMapping("cartitem")
public class CartItemController {

	
	@Autowired
	CartItemService cartItemService;
	
	@GetMapping("/get/{userId}")//get active cartitems only
	public ResponseEntity<ApiResponse<List<CartItem>>> getActiveCartItems(@PathVariable int userId ){
		ApiResponse<List<CartItem>> response = cartItemService.getActiveCartItemsDetails(userId);
		
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<CartItem>> addItem(@RequestBody CartItemDTO cartitem){
		
		ApiResponse<CartItem> response = cartItemService.addCartItem(cartitem) ;
		return ResponseEntity.ok(response);
		
	}
	
}
