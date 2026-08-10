package com.dev.ekart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Orders;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {

	List<Orders> findByUserRel_UserId(Integer userId);

}
