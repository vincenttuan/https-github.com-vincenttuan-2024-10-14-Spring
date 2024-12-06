package com.example.mcdonalds.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcdonalds.model.dto.LoginDTO;
import com.example.mcdonalds.model.dto.UserDTO;
import com.example.mcdonalds.response.ApiResponse;
import com.example.mcdonalds.service.UserService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /auth
 * ----------------------------------
 * POST /auth/login      登入
 * GET  /auth/logout     登出
 * GET  /auth/isLoggedIn 判斷目前的連線是否有登入
 * */
@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginDTO>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		// 登入判斷
		Optional<UserDTO> optUserDTO = userService.login(loginDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
		}
		// 登入成功並存入到 HttpSession 中
		session.setAttribute("userDTO", optUserDTO.get());
		return ResponseEntity.ok(ApiResponse.success("登入成功", null));
	}
	
	
}


