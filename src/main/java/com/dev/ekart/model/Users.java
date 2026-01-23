package com.dev.ekart.model;

import java.time.OffsetDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id", updatable = false)
	Integer userId;
	
	@Column(name="name")
	String name;
	
	@Column(name="email" , unique = true)
	String email;
	
	@Column(name="password")
	String password;
	
	@Column(name="role")
	String role;
	
	@Column(name="status")
	String status;
	
	@CreatedDate
	@Column(name="created_at")
	OffsetDateTime createdAt;
	
	
}
