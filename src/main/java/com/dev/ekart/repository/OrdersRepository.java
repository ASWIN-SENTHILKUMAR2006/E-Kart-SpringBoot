package com.dev.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Orders;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface OrdersRepository  extends JpaRepository<Orders,Integer> {

}
