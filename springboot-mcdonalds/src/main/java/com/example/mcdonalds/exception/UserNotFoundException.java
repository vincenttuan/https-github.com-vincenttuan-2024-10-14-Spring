package com.example.mcdonalds.exception;

// 自訂例外: 查無使用者
public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException() {
		super("查無使用者");
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
}
