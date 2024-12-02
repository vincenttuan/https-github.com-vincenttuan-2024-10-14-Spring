package com.example.mcdonalds.model.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private Long id;
	List<OrderItemDTO> orderItems;
}
