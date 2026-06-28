package com.dev.ekart.serviceImplementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.requestDTO.CartItemRequestDTO;
import com.dev.ekart.responseDTO.CartItemResponseDTO;
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
public ApiResponse<CartItemResponseDTO> addCartItem(CartItemRequestDTO cartitem){
		
		//if(cartitem.getProductId() == 2) throw new RuntimeException("Check");
		
		//get Active Cart
		Optional<Cart> activeCart = cartRepo.CheckActiveCartExist(cartitem.getUserId());
			
			// no active cart
			if(activeCart.isEmpty()) {
				
				Integer userId = cartitem.getUserId();
				ApiResponse<?> newCart = cartService.createCart(userId);	
				// Again get  Active cart After Creation - to give next as input
				activeCart = cartRepo.CheckActiveCartExist(cartitem.getUserId());
				
					// again empty return false on operation
				if(activeCart.isEmpty()) {
					ApiResponse<CartItemResponseDTO> response = new ApiResponse("Cart Creation Failed After Second time - CartItem" , false,null);
					return response;
				}
	   	}
		
	    	Cart cartDetails = activeCart.get();
					
		// get product Details
		Integer productId = cartitem.getProductId();
		Optional <Product> selectedProduct = productRepo.findById(productId);
			//	.orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));;
		
		if(selectedProduct.isEmpty()) {
			// if product not exist
			
			ApiResponse<CartItemResponseDTO> response = new ApiResponse("Product Not Exist to be added as CartItem !!" , false,null);
			return  response;
			
		}else if(selectedProduct.get().getStockQuantity() < cartitem.getQuantity()) {
			ApiResponse<CartItemResponseDTO> response = new ApiResponse("Requested product Quantity not  available !!" , false,null);
			return  response;
			
		}
		
		
		
		
		// Check item Already in cart
		Optional<CartItem> cartItemExists = cartItemRepo.checkCartItemExist(cartitem.getUserId() , cartitem.getProductId());
		
		if(cartItemExists.isPresent()) {
			
			CartItem updateQuantity = cartItemExists.get();
			int newQuantity = (updateQuantity.getQuantity() == null) ? 1 : updateQuantity.getQuantity() + 1; // to avoid NullException
			updateQuantity.setQuantity(newQuantity);
			cartItemRepo.save(updateQuantity);
			
			ApiResponse<CartItemResponseDTO> response = new ApiResponse("CartItem Already Exists!! , Update Quantity" , true,toResponseDTO(updateQuantity));
			
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
			
			ApiResponse<CartItemResponseDTO> response = new ApiResponse("CartItem added Successfully" , true,toResponseDTO(cartItem));
			
			return  response;

		}
	}
	
	public ApiResponse<List<CartItemResponseDTO>> getActiveCartItemsDetails(int userId){
		
		Optional<Cart>  cartInfo = cartRepo.CheckActiveCartExist(userId);
		if (cartInfo.isEmpty()) {
			return new ApiResponse<>("No active cart found for user.", false, null);
		}
		int cartId = cartInfo.get().getCartId();
		List<CartItemResponseDTO> cartItems = cartItemRepo.getCartItemsByCartId(cartId).stream().map(this::toResponseDTO).collect(Collectors.toList());
		ApiResponse<List<CartItemResponseDTO>> response = new ApiResponse("CartItems from active Cart retrieved" , true , cartItems);
		
		return response;
	}

	private CartItemResponseDTO toResponseDTO(CartItem cartItem) {
		if (cartItem == null) {
			return null;
		}
		return new CartItemResponseDTO(
				cartItem.getCartItemId(),
				cartItem.getQuantity(),
				cartItem.getPriceAtBuyTime(),
				cartItem.getProduct() != null ? cartItem.getProduct().getProductId() : null,
				cartItem.getProduct() != null ? cartItem.getProduct().getName() : null,
				cartItem.getCart() != null ? cartItem.getCart().getCartId() : null);
	}

	
}
