package com.example.mcdonalds.exception;

// 非授權例外(登入不成功)
public class UnauthorizedException extends Exception {
	public UnauthorizedException(String message) {
		super(message);
	}
}
