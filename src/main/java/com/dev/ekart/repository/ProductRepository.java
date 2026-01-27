package com.dev.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Product;

/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {

}
