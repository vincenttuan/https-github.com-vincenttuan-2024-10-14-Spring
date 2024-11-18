package com.example.todolist.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.dto.TodoDTO;

@Service
public class TodoServiceImpl implements TodoService {

	@Override
	public List<TodoDTO> getAllTodos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoDTO createTodo(TodoDTO todoDTO) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TodoDTO updateTodo(TodoDTO todoDTO) throws TodoNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteTodo(Long id) throws TodoNotFoundException {
		// TODO Auto-generated method stub
		
	}
	
}
