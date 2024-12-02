package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.OrderDTO;
import com.example.mcdonalds.model.dto.OrderItemDTO;
import com.example.mcdonalds.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Override
	public List<OrderDTO> findOrdersByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<OrderDTO> saveOrder(Long userId, List<OrderItemDTO> orderItems) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
