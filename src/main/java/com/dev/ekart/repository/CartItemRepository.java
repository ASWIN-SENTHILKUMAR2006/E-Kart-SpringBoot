package com.dev.ekart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.CartItem;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface CartItemRepository extends JpaRepository<CartItem , Integer> {

	@Query(value="Select ci.*  FROM cart_item ci	Join cart c 	ON ci.cart_id = c.cart_id where c.customer_id = ?1 AND c.status = 'ACTIVE' AND ci.product_id = ?2  ",nativeQuery=true)
	Optional<CartItem> checkCartItemExist(int userid , int productid);
	
	
	
}
