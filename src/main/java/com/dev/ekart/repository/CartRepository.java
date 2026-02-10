package com.dev.ekart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Cart;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {

	

	
	// ?1 ?2  =>  -- param("name") - >  :name
	
	@Query(value="SELECT * FROM CART WHERE customer_id= :userId AND status=  'ACTIVE' " , nativeQuery = true)
	Optional<Cart> CheckActiveCartExist(@Param("userId") Integer userId);
	
	List<Cart> findByUserRel_UserId(Integer userId);

	/**
	 * @return
	 */
	
	
}
