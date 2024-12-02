package com.example.mcdonalds.exception;

// 自訂例外: 查無使用者
public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException() {
		super("查無商品");
	}
	
	public ProductNotFoundException(String message) {
		super(message);
	}
	
}
