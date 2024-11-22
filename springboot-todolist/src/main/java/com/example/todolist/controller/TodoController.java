package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.model.dto.TodoDTO;
import com.example.todolist.model.entity.Todo;
import com.example.todolist.response.ApiResponse;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * WEB API
 * ------------------------------------------
 * servlet-path: /todolist  (@RequestMapping)
 * ------------------------------------------
 * GET    "/"     獲取所有待辦事項
 * POST   "/"     新增待辦事項
 * PUT    "/{id}" 更新待辦事項
 * DELETE "/{id}" 刪除待辦事項
 * ------------------------------------------
 * */

@RestController
@RequestMapping("/todolist")
@CrossOrigin(origins = "http://localhost:5173") // 跨域請求
public class TodoController {
	
	@Autowired
	private TodoService todoService;
	
	// 獲取所有待辦事項
	@GetMapping
	public ResponseEntity<ApiResponse<List<TodoDTO>>> getAllTodos() {
		List<TodoDTO> todos = todoService.getAllTodos();
		return ResponseEntity.ok(ApiResponse.success("查詢成功", todos));
	}
	
	
}










