package com.dev.ekart.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Aswin Senthilkumar
 *
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

	
	String name;
	String description;
	double price;
	Integer stockQuantity;
	//Integer status = 0 ; // by default assigned as zero in model class - used only on updates
	
	
	//Since you are passing the ID in the URL, you should remove sellerId from the ProductDTO. This prevents confusion and prevents a user from sending one ID in the URL and a different ID in the JSON body.
	//int sellerId; // need to be int since can't handle null , always product need to be added with seller information
	
	
}
