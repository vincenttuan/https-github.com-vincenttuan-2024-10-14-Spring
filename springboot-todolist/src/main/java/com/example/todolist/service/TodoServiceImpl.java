package com.example.todolist.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.todolist.exception.TodoNotFoundException;
import com.example.todolist.model.dto.TodoDTO;
import com.example.todolist.model.entity.Todo;
import com.example.todolist.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService {
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<TodoDTO> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream()
					.map(todo -> modelMapper.map(todo, TodoDTO.class))
					.collect(Collectors.toList());
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
