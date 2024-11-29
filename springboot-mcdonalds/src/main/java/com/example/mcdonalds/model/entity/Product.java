package com.example.mcdonalds.model.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer price;
	
	// 與 ProductImage 建立一對一關聯(單向)
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "product_image_id")
	private ProductImage productImage;
	
	@OneToMany
	private List<OrderItem> orderItems;
	
}
