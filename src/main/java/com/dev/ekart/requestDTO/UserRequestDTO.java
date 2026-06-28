package com.dev.ekart.requestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for creating a user.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDTO {

	private String name;
	private String email;
	private String password;
	private String role;
	private String status;
}
