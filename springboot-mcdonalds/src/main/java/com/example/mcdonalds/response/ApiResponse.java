package com.example.mcdonalds.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
	private Integer status; // 狀態: 200, 404 etc...
	private String message; // 訊息: 查詢成功, 新增成功, 無此資料 etc...
	private T data;         // payload: 實際資料
	
	// 成功回應
	public static <T> ApiResponse<T> success(String message, T data) {
		return new ApiResponse<T>(200, message, data);
	}
	
	// 失敗回應
	public static <T> ApiResponse<T> error(Integer status, String message) {
		return new ApiResponse<T>(status, message, null);
	}
}
