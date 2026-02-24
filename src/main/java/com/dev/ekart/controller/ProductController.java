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
import com.dev.ekart.dto.ProductDTO;
import com.dev.ekart.model.Product;
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
	public ResponseEntity<ApiResponse<List<Product>>> getProductList(){
		ApiResponse<List<Product>>  response = productService.getProductList();
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/add/{SellerId}")
	public ResponseEntity<ApiResponse<Product>> addProduct(@PathVariable int SellerId , @RequestBody ProductDTO productDTO){
		
		ApiResponse<Product>  response = productService.addProduct(SellerId , productDTO);
		return ResponseEntity.ok(response);
	}
}
