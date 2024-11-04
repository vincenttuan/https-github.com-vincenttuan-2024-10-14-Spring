package com.example.demo.model;

// 根據路徑: /person?name=John&age=18&score=90.5&pass=true 來定義物件屬性
public class Person {
	private String name;
	private Integer age;
	private Double score;
	private Boolean pass;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getScore() {
		return score;
	}
	public void setScore(Double score) {
		this.score = score;
	}
	public Boolean getPass() {
		return pass;
	}
	public void setPass(Boolean pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + ", score=" + score + ", pass=" + pass + "]";
	}
	
	
}
