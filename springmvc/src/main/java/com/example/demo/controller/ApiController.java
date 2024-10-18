package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

// 了解各種不同URL與參數的傳遞接收 
@Controller
@RequestMapping("/api")
public class ApiController {
	
	/** 
	 * 1.歡迎頁 
	 * 路徑: /welcome
	 * 路徑: /home
	 * 網址: http://localhost:8080/api/welcome
	 * 網址: http://localhost:8080/api/home
	 */
	@GetMapping(value = {"/welcome", "/home"})
	@ResponseBody
	public String welcome() {
		return "Welcome";
	}
	
	/**
	 * 2. ?帶參數
	 * 路徑: /greet?name=John&age=18
	 * 路徑: /greet?name=Mary
	 * 網址: http://localhost:8080/api/greet?name=John&age=18
	 * 網址: http://localhost:8080/api/greet?name=Mary
	 * 限制: name 參數是一定要加上的
	 *      age 參數不一定要有(若沒有 age 參數則會給初始值 = 0)
	 * */
	@GetMapping("/greet")
	@ResponseBody
	public String greet(@RequestParam(value = "name", required = true) String name,
						@RequestParam(value = "age", required = false, defaultValue = "0") Integer age) {
		return String.format("Hi %s %d", name, age);
	}
	
	/**
	 * 3. Lab 練習
	 * 路徑: /bmi?h=170.0&w=60.0
	 * 網址: http://localhost:8080/api/bmi?h=170.0&w=60.0
	 * 執行結果: bmi = 20.76
	 * */
	@GetMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam(value = "h") Double h, 
					  @RequestParam(value = "w") Double w) {
		double bmi = w / Math.pow(h/100, 2);
		return String.format("bmi = %.2f", bmi);
	}
}
