package com.dev.ekart.service;

import java.util.List;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.ProductDTO;
import com.dev.ekart.model.Product;

/**
 * @author Aswin Senthilkumar
 *
 */
public interface ProductService {

	/**
	 * @return
	 */
	ApiResponse<List<Product>> getProductList();

	/**
	 * @param sellerId 
	 * @param productDTO
	 * @return
	 */
	ApiResponse<Product> addProduct(int sellerId, ProductDTO productDTO);

	

}
