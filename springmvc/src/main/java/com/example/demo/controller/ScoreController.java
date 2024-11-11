package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(value = "*")
public class ScoreController {
	
	@GetMapping("/scores")
	public String getScores() {
		return """
				[
			        {"id": 1, "name": "Alice", "score": 100},
			        {"id": 2, "name": "Bob", "score": 55},
			        {"id": 3, "name": "John", "score": 80},
			        {"id": 4, "name": "Mary", "score": 40},
			        {"id": 5, "name": "cha", "score": 70}
			    ]
				""".trim();
	}
}
