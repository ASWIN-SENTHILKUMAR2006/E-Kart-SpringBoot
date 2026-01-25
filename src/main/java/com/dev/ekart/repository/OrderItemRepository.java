package com.dev.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Orders;

@Repository
public interface OrderItemRepository extends JpaRepository<Orders,Integer> {

}
