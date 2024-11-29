package com.example.mcdonalds.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_item")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 訂單項目 id
	
	@Column
	private Integer quantity; // 商品數量 
	
	// order_item 與 order 的關係是多對一
	@ManyToOne
	@JoinColumn(name = "order_id") // 對應欄位
	private Order order; // 一個訂單
	
	// order_item 與 product 的關係是多對一
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
	
}






















