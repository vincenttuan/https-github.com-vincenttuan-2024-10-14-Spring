package com.example.demo.controller;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	 * 3. Lab 練習 I
	 * 路徑: /bmi?h=170.0&w=60.0
	 * 網址: http://localhost:8080/api/bmi?h=170.0&w=60.0
	 * 執行結果: bmi = 20.76
	 * */
	@GetMapping("/bmi")
	@ResponseBody
	public String bmi(@RequestParam(name = "h") Double h, 
					  @RequestParam(name = "w") Double w) {
		double bmi = w / Math.pow(h/100, 2);
		return String.format("bmi = %.2f", bmi);
	}
	
	/**
	 * 3. Lab 練習 II
	 * 路徑: /add?x=10&y=30
	 * 網址: http://localhost:8080/api/add?x=10&y=30
	 * 執行結果: result = 40
	 * */
	public String add(@RequestParam(name = "x") Integer x, 
			  		  @RequestParam(name = "y") Integer y) {
		int sum = x + y;
		return String.format("result = %d", sum);
	}
	
	/**
	 * 4. 同名多筆的資料
	 * 路徑: /age?age=17&age=21&age=20
	 * 網址: http://localhost:8080/api/age?age=17&age=21&age=20
	 * 計算出平均年齡
	 */
	@GetMapping(value = "/age", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getAverageAge(@RequestParam("age") List<Integer> ages) {
		double avg = ages.stream().mapToInt(Integer::intValue).average().getAsDouble();
		return String.format("平均年齡: %.1f", avg);
	}
	
	/*
	 * 5. Lab 練習: 得到多筆 score 資料
	 * 路徑: "/exam?score=80&score=100&score=50&score=70&score=30"
	 * 網址: http://localhost:8080/api/exam?score=80&score=100&score=50&score=70&score=30
	 * 請自行設計一個方法，此方法可以
	 * 印出: 最高分=?、最低分=?、平均=?、總分=?、及格分數=?、不及格=?
	 * (支援中文字印出) 
	 * 提示: IntSummaryStatistics, Collectors.partitioningBy
	 * */
	@GetMapping(value = "/exam", produces = "text/plain;charset=utf-8")
	@ResponseBody
	public String getExamInfo(@RequestParam("score") List<Integer> scores) {
		// 統計資料
		IntSummaryStatistics statistics = scores.stream().mapToInt(Integer::intValue).summaryStatistics();
		// 及格分數
		//List<Integer> passList = scores.stream().filter(score -> score >= 60).collect(Collectors.toList());
		// 不及格分數
		//List<Integer> failList = scores.stream().filter(score -> score < 60).collect(Collectors.toList());
		// 利用 Collectors.partitioningBy 來分組
		// true: 及格, false: 不及格
		Map<Boolean, List<Integer>> resultMap = scores.stream().collect(Collectors.partitioningBy(score -> score >= 60));
		
		return String.format("最高分=%d、最低分=%d、平均=%.1f、總分=%d、及格分數=%s、不及格=%s", 
				statistics.getMax(), statistics.getMin(), statistics.getAverage(), statistics.getSum(),
				resultMap.get(true), resultMap.get(false));
	}
	
	/**
	 * 6. 多筆參數轉 Map
	 * 路徑: /product?name=Math&price=12.5&amount=10&outOf=true
	 * 路徑: /product?name=Comics&price=10.5&amount=20&outOf=false
	 * 網址: http://localhost:8080/api/product?name=Math&price=12.5&amount=10&outOf=true
	 * 網址: http://localhost:8080/api/product?name=Comics&price=10.5&amount=20&outOf=false
	 * */
	@GetMapping("/product")
	@ResponseBody
	public String getProduct(@RequestParam Map<String, Object> productMap) {
		return "product map: " + productMap;
	}
	
	/**
	 * 7. 多筆參數轉 bean
	 * 路徑: /person?name=John&age=18&score=90.5&pass=true
	 * 路徑: /person?name=Mary&age=17&score=40.5&pass=false
	 * 網址: http://localhost:8080/api/person?name=John&age=18&score=90.5&pass=true
	 * 網址: http://localhost:8080/api/person?name=Mary&age=17&score=40.5&pass=false
	 */
	
	
}
