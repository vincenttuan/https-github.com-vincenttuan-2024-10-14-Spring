package com.example.mcdonalds.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcdonalds.model.dto.OrderDTO;
import com.example.mcdonalds.model.dto.OrderItemDTO;
import com.example.mcdonalds.model.dto.UserDTO;
import com.example.mcdonalds.model.entity.OrderItem;
import com.example.mcdonalds.response.ApiResponse;
import com.example.mcdonalds.service.OrderService;

import jakarta.servlet.http.HttpSession;

/*
 * WEB REST API (必須要登入才能使用)
 * ----------------------------------
 * Servlet-Path: /orders
 * ----------------------------------
 * GET   /orders         查詢該使用者所有商品(多筆)
 * POST  /orders/checkout 該使用者進行結帳
 * */
@RestController
@RequestMapping("/orders")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	// 查詢該使用者所有商品(多筆)
	@GetMapping
	public ResponseEntity<ApiResponse<List<OrderDTO>>> getAllOrders(HttpSession session) {
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		List<OrderDTO> orderDTOs = orderService.findOrdersByUserId(userDTO.getId());
		return ResponseEntity.ok(ApiResponse.success("查詢成功", orderDTOs));
	}
	
	// 該使用者進行結帳
	@PostMapping("/checkout")
	public ResponseEntity<ApiResponse<OrderDTO>> addOrder(@RequestBody List<OrderItemDTO> items, HttpSession session) {
		UserDTO userDTO = (UserDTO)session.getAttribute("userDTO");
		Optional<OrderDTO> optOrderDTO = orderService.saveOrder(userDTO.getId(), items);
		if(optOrderDTO.isEmpty()) {
			return ResponseEntity.status(500).body(ApiResponse.error(500, "新增訂單失敗"));
		}
		return ResponseEntity.ok(ApiResponse.success("新增訂單成功", optOrderDTO.get()));
	}
	
	
}
