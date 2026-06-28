package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.ProductRequestDTO;
import com.dev.ekart.responseDTO.ProductResponseDTO;
import com.dev.ekart.model.Product;
import com.dev.ekart.model.Users;
import com.dev.ekart.repository.ProductRepository;
import com.dev.ekart.repository.UserRepository;
import com.dev.ekart.service.ProductService;

/**
 * @author Aswin Senthilkumar
 *
 */

@Service
public class ProductServiceImpl  implements ProductService{

	@Autowired
	ProductRepository  productRepo;
	
	@Autowired
	UserRepository userRepo;
	
	
	public ApiResponse<List<ProductResponseDTO>> getProductList(){
		List<ProductResponseDTO> productList = productRepo.findAll().stream().map(this::toResponseDTO).collect(Collectors.toList());
		ApiResponse <List<ProductResponseDTO>> response = new ApiResponse<>("Retrieved List of Products",true,productList);
		return response;
	}
	
	public ApiResponse<ProductResponseDTO> addProduct(int sellerId, ProductRequestDTO productDTO){
		
		//check User is present and Seller 
		Optional<Users> seller =  userRepo.checkSeller(sellerId);
		
		if(seller.isPresent()) {
			
		// DTO Mapping
		Product newProduct = new Product();
		newProduct.setName(productDTO.getName());
		newProduct.setDescription(productDTO.getDescription());
		newProduct.setPrice(productDTO.getPrice());
		newProduct.setStockQuantity(productDTO.getStockQuantity());
		newProduct.setSellerRel(seller.get());
		newProduct.setStatus(0);
		
		
		Product product = productRepo.save(newProduct);
		ApiResponse<ProductResponseDTO> response = new ApiResponse<>("Product added Successfully!!",true,toResponseDTO(product));
		return response;
		
		}else {
			ApiResponse<ProductResponseDTO> response = new ApiResponse<>("Seller Not Found-Can't add product",false,null);
			return response;
		}
	}
	
	
	public ApiResponse<String> deleteProduct(int id){
		
		Optional<Product> product = productRepo.findById(id);
		String message ;
		Boolean status;
		if(product.isPresent()) {
 		productRepo.deleteById(id);
 		message = "Product deleted : ID - " + id +" Product - Name : "+ product.get().getName() ;
 		status = true;
		}else {
			message = "No Product Found in ID -  " + id;
			status = false;
		}
		
		ApiResponse<String> response = new ApiResponse<>(message,true , null  );
		
		return response;
	}

	private ProductResponseDTO toResponseDTO(Product product) {
		if (product == null) {
			return null;
		}
		return new ProductResponseDTO(
				product.getProductId(),
				product.getName(),
				product.getDescription(),
				product.getPrice(),
				product.getStockQuantity(),
				product.getStatus(),
				product.getCreatedAt(),
				product.getSellerRel() != null ? product.getSellerRel().getUserId() : null);
	}
	
}
