package com.example.mcdonalds.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "`order`")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; // 訂單 id
	
	@OneToMany
	private List<OrderItem> items; // 多筆訂單細目
	
	// order 與 user 的關係是多對一
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}

















