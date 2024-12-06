package com.example.mcdonalds.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.mcdonalds.response.ApiResponse;

@ControllerAdvice // 用來處理全局的 Controller, 例如:例外處理
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<String>> handleException(Exception ex) {
		ApiResponse<String> response = ApiResponse.error(500, ex.getMessage());
		return ResponseEntity.status(500).body(response);
	}
	
}
