package com.example.demo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Server 端對遞給 Client 端的統一結構(含錯誤)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse<T> {
	
	private int status;     // 狀態 　　例如: 200
	private String message; // 狀態訊息 例如: 查詢成功
	private T data; // payload　實際資訊
	
	// 成功回應
	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<>(200, message, data);
	}
	
	// 錯誤回應
	public static <T> ApiResponse<T> error(int status, String message) {
		return new ApiResponse<>(status, message, null);
	}
}
