package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.OrderDTO;
import com.example.mcdonalds.model.dto.OrderItemDTO;
import com.example.mcdonalds.repository.OrderItemRepository;
import com.example.mcdonalds.repository.OrderRepository;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;
import com.example.mcdonalds.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
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
