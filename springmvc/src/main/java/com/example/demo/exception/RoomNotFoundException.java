package com.example.demo.exception;

public class RoomNotFoundException extends RuntimeException {
	
	public RoomNotFoundException(String message) {
		super(message);
	}
}
