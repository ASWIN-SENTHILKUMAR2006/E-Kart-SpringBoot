package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.ProductDTO;
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
	
	
	public ApiResponse<List<Product>> getProductList(){
		List<Product> productList =  productRepo.findAll();
		ApiResponse <List<Product>> response = new ApiResponse<>("Retrieved List of Products",true,productList);
		return response;
	}
	
	public ApiResponse<Product> addProduct(int sellerId, ProductDTO productDTO){
		
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
		ApiResponse<Product> response = new ApiResponse<>("Product added Successfully!!",true,product);
		return response;
		
		}else {
			ApiResponse<Product> response = new ApiResponse<>("Seller Not Found-Can't add product",false,null);
			return response;
		}
	}
	
}
