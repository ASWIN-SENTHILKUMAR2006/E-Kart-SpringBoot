package com.dev.ekart.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.CartItemDTO;
import com.dev.ekart.model.CartItem;
import com.dev.ekart.model.Product;
import com.dev.ekart.repository.CartItemRepository;
import com.dev.ekart.repository.ProductRepository;
import com.dev.ekart.service.CartItemService;

/**
 * @author Aswin Senthilkumar
 *
 */
@Service
public class CartItemImpl implements CartItemService{

	@Autowired
	CartItemRepository cartItemRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	
public ApiResponse<CartItem> addCartItem(CartItemDTO cartitem){
		
		// get product Details
		Integer productId = cartitem.getProductId();
		Product selectedProduct = productRepo.getById(productId);
			//	.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));;
		
		
		CartItem newCartItem = new CartItem();
		
		newCartItem.setProduct(selectedProduct);
		// unwanted field
		newCartItem.setPriceAtBuyTime(selectedProduct.getPrice());
		newCartItem.setQuantity(cartitem.getQuantity());
		
		CartItem cartItem = cartItemRepo.save(newCartItem)  ;
		
		ApiResponse<CartItem> response = new ApiResponse("CartItem added Successfully" , true,cartItem);
		return  response;
	}
	
	
}
