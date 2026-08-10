package com.dev.ekart.serviceImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.ekart.ApiResponse;
import com.dev.ekart.model.Cart;
import com.dev.ekart.model.CartItem;
import com.dev.ekart.model.OrderItem;
import com.dev.ekart.model.Orders;
import com.dev.ekart.model.Product;
import com.dev.ekart.repository.CartRepository;
import com.dev.ekart.repository.OrderItemRepository;
import com.dev.ekart.repository.OrdersRepository;
import com.dev.ekart.repository.ProductRepository;
import com.dev.ekart.responseDTO.OrderItemResponseDTO;
import com.dev.ekart.responseDTO.OrderResponseDTO;
import com.dev.ekart.service.OrderService;

/**
 * @author Aswin Senthilkumar
 *
 */

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	CartRepository cartRepo;

	@Autowired
	ProductRepository productRepo;

	@Autowired
	OrdersRepository ordersRepo;

	@Autowired
	OrderItemRepository orderItemRepo;

	@Transactional
	public ApiResponse<OrderResponseDTO> checkout(int userId) {

		Optional<Cart> activeCart = cartRepo.CheckActiveCartExist(userId);
		if (activeCart.isEmpty()) {
			ApiResponse<OrderResponseDTO> response = new ApiResponse<>("User's Does not have any Active Carts.", false, null);
			return response;
		}

		Cart cart = activeCart.get();
		List<CartItem> cartItems = cart.getCartItem();

		if (cartItems == null || cartItems.isEmpty()) {
			ApiResponse<OrderResponseDTO> response = new ApiResponse<>("Active Cart has no items to Checkout.", false, null);
			return response;
		}

		for (CartItem item : cartItems) {

			Integer productId = item.getProduct().getProductId();
			Optional<Product> productDetails = productRepo.findById(productId);

			if (productDetails.isEmpty()) {
				throw new RuntimeException("Product Not Exist for CartItem !! , Checkout Failed - Product Id : " + productId);
			}

			if (productDetails.get().getStockQuantity() < item.getQuantity()) {
				throw new RuntimeException("Requested product Quantity not available !! , Checkout Failed - Product : " + productDetails.get().getName());
			}
		}

		Double totalAmount = 0.0;
		List<OrderItem> orderItems = new ArrayList<>();

		Orders newOrder = new Orders();
		newOrder.setOrderStatus("PLACED");
		newOrder.setUserRel(cart.getUserRel());

		for (CartItem item : cartItems) {

			Product product = productRepo.findById(item.getProduct().getProductId()).get();

			int updatedStock = product.getStockQuantity() - item.getQuantity();
			product.setStockQuantity(updatedStock);
			productRepo.save(product);

			OrderItem orderItem = new OrderItem();
			orderItem.setProductName(product.getName());
			orderItem.setPriceAtPurchase(product.getPrice());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSellerId(product.getSellerRel() != null ? product.getSellerRel().getUserId() : null);
			orderItem.setOrderId(newOrder);

			orderItems.add(orderItem);

			totalAmount = totalAmount + (product.getPrice() * item.getQuantity());
		}

		newOrder.setTotalAmount(totalAmount);
		newOrder.setOrderItems(orderItems);

		Orders savedOrder = ordersRepo.save(newOrder);
		orderItemRepo.saveAll(orderItems);

		cart.setStatus("CHECKED_OUT");
		cartRepo.save(cart);

		ApiResponse<OrderResponseDTO> response = new ApiResponse<>("Order Placed Successfully", true, toResponseDTO(savedOrder));
		return response;
	}

	private OrderResponseDTO toResponseDTO(Orders order) {
		if (order == null) {
			return null;
		}
		List<OrderItemResponseDTO> orderItemDTOs = order.getOrderItems() == null ? new ArrayList<>()
				: order.getOrderItems().stream().map(this::toResponseDTO).collect(Collectors.toList());

		return new OrderResponseDTO(
				order.getOrderId(),
				order.getOrderStatus(),
				order.getTotalAmount(),
				order.getCreatedAt(),
				order.getUserRel() != null ? order.getUserRel().getUserId() : null,
				orderItemDTOs);
	}

	private OrderItemResponseDTO toResponseDTO(OrderItem orderItem) {
		if (orderItem == null) {
			return null;
		}
		return new OrderItemResponseDTO(
				orderItem.getOrderItemId(),
				orderItem.getProductName(),
				orderItem.getPriceAtPurchase(),
				orderItem.getQuantity(),
				orderItem.getSellerId());
	}

}
