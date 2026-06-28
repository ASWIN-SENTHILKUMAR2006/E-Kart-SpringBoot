package com.dev.ekart.responseDTO;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response payload for user details.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

	private Integer userId;
	private String name;
	private String email;
	private String role;
	private String status;
	private LocalDateTime createdAt;
}
