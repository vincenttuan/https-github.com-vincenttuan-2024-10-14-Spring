package com.example.mcdonalds.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class Order {
	private Long id;
	List<OrderItemDTO> orderItems;
}
