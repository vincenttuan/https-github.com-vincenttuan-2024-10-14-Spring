package com.example.todolist.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Table(name = "todo") // 預設: 自動建立 table
public class Todo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 由資料庫來決定 id 生成策略
	private Long id;
	
	@Column(name = "text", length = 255, nullable = true, unique = false) // 預設: 可不寫
	private String text;
	
	@Column(name = "completed") // 預設: 可不寫
	private Boolean completed;
	
}
