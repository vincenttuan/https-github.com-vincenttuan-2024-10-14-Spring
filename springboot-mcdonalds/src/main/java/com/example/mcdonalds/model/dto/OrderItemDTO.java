package com.example.mcdonalds.model.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Long id;
	private Integer quantity;
	private ProductDTO product; // 不要使用 productDTO 命名, 如此可以預設透過 modelMapper 進行對應
}
