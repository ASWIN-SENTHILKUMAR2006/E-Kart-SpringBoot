package com.dev.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Payment;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface PaymentRepository extends JpaRepository<Payment , Integer> {

	
}
