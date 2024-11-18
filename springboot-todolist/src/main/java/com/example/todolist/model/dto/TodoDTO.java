package com.example.todolist.model.dto;

import com.example.todolist.model.entity.Todo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {
	private Long id;
	private String text;
	private Boolean completed;
}
