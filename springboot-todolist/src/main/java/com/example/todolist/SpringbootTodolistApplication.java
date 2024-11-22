package com.example.todolist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
//@ComponentScan(basePackages = {"com.example.aaa"}) // 掃描其他資料夾
public class SpringbootTodolistApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTodolistApplication.class, args);
	}

}
