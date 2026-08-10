package com.dev.ekart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.responseDTO.OrderResponseDTO;
import com.dev.ekart.service.OrderService;

/**
 * @author Aswin Senthilkumar
 *
 */

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/checkout/{userId}")
	public ResponseEntity<ApiResponse<OrderResponseDTO>> checkout(@PathVariable int userId){

		ApiResponse<OrderResponseDTO> response = orderService.checkout(userId);

		if(response.getSuccess() == false) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		return ResponseEntity.ok(response);
	}

}
