package com.example.mcdonalds.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO loginDTO, HttpSession session) {
		// 登入判斷
		Optional<UserDTO> optUserDTO = userService.login(loginDTO);
		if(optUserDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "登入失敗"));
		}
		// 登入成功並存入到 HttpSession 中
		session.setAttribute("userDTO", optUserDTO.get());
		return ResponseEntity.ok(ApiResponse.success("登入成功", "OK"));
	}
	
	@GetMapping("/logout")
	public ResponseEntity<ApiResponse<String>> logout(HttpSession session) {
		session.invalidate(); // session 失效
		return ResponseEntity.ok(ApiResponse.success("登出成功", "OK"));
	}
	
	@GetMapping("/isLoggedIn")
	public ResponseEntity<ApiResponse<LoginDTO>> isLoggedIn(HttpSession session) {
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		LoginDTO loginDTO = new LoginDTO();
		if(userDTO == null) {
			loginDTO.setIsLoggedIn(false);
			return ResponseEntity.ok(ApiResponse.success("無登入資訊", loginDTO));
		}
		loginDTO.setIsLoggedIn(true); // 已登入
		loginDTO.setUsername(userDTO.getUsername()); // 登入者
		return ResponseEntity.ok(ApiResponse.success("已登入資訊", loginDTO));
	}
}


