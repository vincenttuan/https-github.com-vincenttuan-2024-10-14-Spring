package com.example.mcdonalds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcdonalds.model.dto.FavoriteProductDTO;
import com.example.mcdonalds.model.dto.UserDTO;
import com.example.mcdonalds.response.ApiResponse;
import com.example.mcdonalds.service.UserService;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/favorites")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class FavoriteController {
	@Autowired
	private UserService userService;
	
	// 獲取"登入用戶"所關注清單
	@GetMapping
	public ResponseEntity<ApiResponse<List<FavoriteProductDTO>>> getFavoriteProducts(HttpSession session) {
		Long userId = ((UserDTO)session.getAttribute("userDTO")).getId();
		List<FavoriteProductDTO> favorites = userService.getFavoriteProducts(userId);
		return ResponseEntity.ok(ApiResponse.success("獲取關注清單成功", favorites));
	}
	
	// 獲取商品被關注清單
	
	// "登入用戶"加入關注的商品
	
	// "登入用戶"取消關注的商品
	
}
