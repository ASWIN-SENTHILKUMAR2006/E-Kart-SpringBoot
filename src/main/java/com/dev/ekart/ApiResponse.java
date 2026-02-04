package com.dev.ekart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Aswin Senthilkumar
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {

	private String message;
	private Boolean success;
	private  T data;
	
	
	
	
	
	
}
