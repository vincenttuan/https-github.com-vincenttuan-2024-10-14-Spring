package com.example.todolist.exception;

// 自訂例外
public class TodoNotFoundException extends Exception {
	public TodoNotFoundException(String message) {
		super(message);
	}
}
