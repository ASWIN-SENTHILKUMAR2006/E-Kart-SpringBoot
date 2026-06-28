package com.dev.ekart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.ProductRequestDTO;
import com.dev.ekart.responseDTO.ProductResponseDTO;
import com.dev.ekart.service.ProductService;

/**
 * @author Aswin Senthilkumar
 *
 */

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired			
	ProductService productService;
	
	@GetMapping("/list")
	public ResponseEntity<ApiResponse<List<ProductResponseDTO>>> getProductList(){
		ApiResponse<List<ProductResponseDTO>>  response = productService.getProductList();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/add/{SellerId}")
	public ResponseEntity<ApiResponse<ProductResponseDTO>> addProduct(@PathVariable int SellerId , @RequestBody ProductRequestDTO productDTO){
		
		ApiResponse<ProductResponseDTO>  response = productService.addProduct(SellerId , productDTO);
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse<String>> deleteProduct(@PathVariable int id){
		ApiResponse<String> response = productService.deleteProduct(id);
		return ResponseEntity.ok(response);
	}
}
