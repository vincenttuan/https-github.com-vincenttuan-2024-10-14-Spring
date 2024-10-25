package com.example.demo.exception;

public class RoomAlreadyExistsException extends RuntimeException {
	public RoomAlreadyExistsException(String message) {
		super(message);
	}
}
