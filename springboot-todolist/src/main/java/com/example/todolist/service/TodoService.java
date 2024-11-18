package com.example.todolist.service;

import java.util.List;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.dto.TodoDTO;

public interface TodoService {
	
	// 取得所有代辦事項
	public List<TodoDTO> getAllTodos();
	
	// 新增代辦事項
	public TodoDTO createTodo(TodoDTO todoDTO);
	
	// 更新代辦事項
	public TodoDTO updateTodo(TodoDTO todoDTO) throws TodoNotFoundException;
	
	// 刪除代辦事項
	public void deleteTodo(Long id) throws TodoNotFoundException;
	
}
