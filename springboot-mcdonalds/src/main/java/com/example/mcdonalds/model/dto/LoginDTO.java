package com.example.mcdonalds.model.dto;

import lombok.Data;

@Data
public class LoginDTO {
	private String username;
	private String password;
	private Boolean isLoggedIn; // 是否已登入 
	
}
