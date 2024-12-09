package com.example.mcdonalds.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcdonalds.model.dto.FavoriteProductDTO;
import com.example.mcdonalds.model.dto.FavoriteUserDTO;
import com.example.mcdonalds.model.dto.UserDTO;
import com.example.mcdonalds.response.ApiResponse;
import com.example.mcdonalds.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API (必須要登入才能使用)
 * ----------------------------------------------------------
 * Servlet-Path: /favorites
 * ----------------------------------------------------------
 * GET     /favorites                     獲取"登入用戶"所關注清單
 * GET     /favorites/product/{productId} 獲取商品被關注清單 
 * POST    /favorites/{productId}         "登入用戶"加入關注的商品
 * DELETE  /favorites/{productId}         "登入用戶"取消所關注的商品
 * */
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
	@GetMapping("/product/{productId}")
	public ResponseEntity<ApiResponse<List<FavoriteUserDTO>>> getFavoriteUsers(@PathVariable Long productId) {
		List<FavoriteUserDTO> users = userService.getFavoriteUsers(productId);
		return ResponseEntity.ok(ApiResponse.success("獲取該商品關注的使用者列表", users));
	}
	
	// "登入用戶"加入關注的商品
	
	// "登入用戶"取消關注的商品
	
}
