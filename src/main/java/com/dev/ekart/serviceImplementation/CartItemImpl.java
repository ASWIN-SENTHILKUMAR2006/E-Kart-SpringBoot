package com.dev.ekart.serviceImplementation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.dto.CartItemDTO;
import com.dev.ekart.model.Cart;
import com.dev.ekart.model.CartItem;
import com.dev.ekart.model.Product;
import com.dev.ekart.repository.CartItemRepository;
import com.dev.ekart.repository.CartRepository;
import com.dev.ekart.repository.ProductRepository;
import com.dev.ekart.service.CartItemService;
import com.dev.ekart.service.CartService;

import jakarta.transaction.Transactional;

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
	
	@Autowired
	CartRepository cartRepo;
	
	@Autowired
	CartService cartService;
	

	@Transactional
public ApiResponse<CartItem> addCartItem(CartItemDTO cartitem){
	
		//get Active Cart
		Optional<Cart> activeCart = cartRepo.CheckActiveCartExist(cartitem.getUserId());
			
			// no active cart
			if(activeCart.isEmpty()) {
				Integer userId = cartitem.getUserId();
				ApiResponse<Cart> newCart = cartService.createCart(userId);	
				// Again get  Active cart After Creation - to give next as input
				activeCart = cartRepo.CheckActiveCartExist(cartitem.getUserId());
				
					// again empty return false on operation
				if(activeCart.isEmpty()) {
					ApiResponse<CartItem> response = new ApiResponse("Cart Creation Failed After Second time - CartItem" , false,null);
				}
	   	}
		
	    	Cart cartDetails = activeCart.get();
					
		// get product Details
		Integer productId = cartitem.getProductId();
		Optional <Product> selectedProduct = productRepo.findById(productId);
			//	.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));;
		
		if(selectedProduct.isEmpty()) {
			// if product not exist
			
			ApiResponse<CartItem> response = new ApiResponse("Product Not Exist to be added as CartItem !!" , false,null);
			return  response;
			
		}
		
		
		
		
		// Check item Already in cart
		Optional<CartItem> cartItemExists = cartItemRepo.checkCartItemExist(cartitem.getUserId() , cartitem.getProductId());
		
		if(cartItemExists.isPresent()) {
			
			CartItem updateQuantity = cartItemExists.get();
			int newQuantity = (updateQuantity.getQuantity() == null) ? 1 : updateQuantity.getQuantity() + 1; // to avoid NullException
			updateQuantity.setQuantity(newQuantity);
			cartItemRepo.save(updateQuantity);
			
			ApiResponse<CartItem> response = new ApiResponse("CartItem Already Exists!! , Update Quantity" , true,updateQuantity);
			
			return  response;
			
		}else {
			
			CartItem newCartItem = new CartItem();
			// selected Product is Optional
			newCartItem.setProduct(selectedProduct.get());
			// unwanted field
			newCartItem.setPriceAtBuyTime(selectedProduct.get().getPrice());
			newCartItem.setQuantity(cartitem.getQuantity());
			newCartItem.setCart(cartDetails);
			
			CartItem cartItem = cartItemRepo.save(newCartItem)  ;
			
			ApiResponse<CartItem> response = new ApiResponse("CartItem added Successfully" , true,cartItem);
			
			return  response;

		}
	}
}
