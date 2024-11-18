package com.example.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.todolist.model.entity.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {
	// 內建基本的 CRUD
}
