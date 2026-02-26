package com.dev.ekart.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dev.ekart.model.Users;


/**
 * @author Aswin Senthilkumar
 *
 */

@Repository
public interface UserRepository  extends JpaRepository<Users,Integer>{

	@Query(value="SELECT * FROM users WHERE user_id = ?1 and role ='SELLER'  ",nativeQuery=true)
	public Optional<Users> checkSeller(@Param("sellerId") int sellerId);

	/**
	 * @param email
	 * @return
	 */
	public Optional<Users> findByEmail(String email); 
	
}
