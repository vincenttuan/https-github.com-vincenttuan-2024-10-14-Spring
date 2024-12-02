package com.example.mcdonalds.model.dto;

import lombok.Data;

@Data
public class ProductDTO {
	private Long id; // 來自於 Product
	private String name; // 來自於 Product
	private Integer price; // 來自於 Product
	
	private String imageBase64; // 來自於 ProductImage 
}
