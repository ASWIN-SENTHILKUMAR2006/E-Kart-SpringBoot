package com.dev.ekart.service;

import java.util.List;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.ProductRequestDTO;
import com.dev.ekart.responseDTO.ProductResponseDTO;

/**
 * @author Aswin Senthilkumar
 *
 */
public interface ProductService {

	/**
	 * @return
	 */
	ApiResponse<List<ProductResponseDTO>> getProductList();

	/**
	 * @param sellerId 
	 * @param productDTO
	 * @return
	 */
	ApiResponse<ProductResponseDTO> addProduct(int sellerId, ProductRequestDTO productDTO);
	
	
	/**
	 * @param sellerId 
	 * @return
	 */
	 ApiResponse<String> deleteProduct(int id);

	

}
