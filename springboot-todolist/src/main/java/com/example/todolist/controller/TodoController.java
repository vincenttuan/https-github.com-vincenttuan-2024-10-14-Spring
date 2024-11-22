package com.example.todolist.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.dto.TodoDTO;
import com.example.todolist.model.entity.Todo;
import com.example.todolist.response.ApiResponse;
import com.example.todolist.service.TodoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// 新增待辦事項
	@PostMapping
	public ResponseEntity<ApiResponse<TodoDTO>> createTodo(@RequestBody TodoDTO todoDTO) {
		TodoDTO createdTodoDTO = todoService.createTodo(todoDTO);
		return ResponseEntity.ok(ApiResponse.success("新增成功", createdTodoDTO));
	}
	
	// 更新待辦事項
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse<TodoDTO>> updateTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO)  
			throws TodoNotFoundException {
		todoDTO.setId(id);
		TodoDTO updatedTodoDTO = todoService.updateTodo(todoDTO);
		return ResponseEntity.ok(ApiResponse.success("修改成功", updatedTodoDTO));
	}
	
	// 刪除待辦事項
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse<Void>> deleteTodo(@PathVariable Long id, @RequestBody TodoDTO todoDTO)  
			throws TodoNotFoundException {
		todoService.deleteTodo(id);
		return ResponseEntity.ok(ApiResponse.success("刪除成功", null));
	}
	
	// 統一處理異常狀況(目的: 產生統一的錯誤結構給前端)
	@ExceptionHandler(TodoNotFoundException.class)
	public ResponseEntity<ApiResponse<Void>> handleTodoException(TodoNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ApiResponse.error(HttpStatus.NOT_FOUND.value(), e.getMessage()));
	}
}










