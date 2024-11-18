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
	
	// 取得所有代辦事項
	@Override
	public List<TodoDTO> getAllTodos() {
		List<Todo> todos = todoRepository.findAll();
		return todos.stream()
					.map(todo -> modelMapper.map(todo, TodoDTO.class))
					.collect(Collectors.toList());
	}
	
	// 新增代辦事項
	@Override
	public TodoDTO createTodo(TodoDTO todoDTO) {
		// 將 TodoDTO 轉 Todo
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		// 儲存
		Todo savedTodo = todoRepository.save(todo);
		// 將 Todo 轉 TodoDto
		return modelMapper.map(savedTodo, TodoDTO.class);
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
