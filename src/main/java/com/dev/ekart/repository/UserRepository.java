package com.dev.ekart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Users;


/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface UserRepository  extends JpaRepository<Users,Integer>{

}
