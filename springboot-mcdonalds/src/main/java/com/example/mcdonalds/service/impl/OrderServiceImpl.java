package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.OrderDTO;
import com.example.mcdonalds.model.dto.OrderItemDTO;
import com.example.mcdonalds.model.entity.Order;
import com.example.mcdonalds.model.entity.OrderItem;
import com.example.mcdonalds.model.entity.User;
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
		return orderRepository.findByUserId(userId) // List<Order>
							  .stream()             // ...Order
							  .map(order -> modelMapper.map(order, OrderDTO.class)) // ...OrderDTO
							  .toList();
	}
	
	// 非聯集
	/*
	@Override
	public Optional<OrderDTO> saveOrder(Long userId, List<OrderItemDTO> orderItems) {
		// 1. 得到 user
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) return null;
		User user = optUser.get();
		
		// 2. 建立訂單
		Order order = new Order();
		order.setUser(user); // 設定關聯
		
		// 3. 儲存 order
		orderRepository.save(order); // 非聯集操作時要加入
		
		// 4. 建立訂單項目並且逐一儲存
		orderItems.forEach(item -> { // item 的型別是 OrderItemDTO
			OrderItem orderItem = modelMapper.map(item, OrderItem.class); // OrderItemDTO 轉 OrderItem
			orderItem.setOrder(order); // 設定關聯關係
			orderItemRepository.save(orderItem); // 儲存
		});
		
		return Optional.of(modelMapper.map(order, OrderDTO.class)); // Order 轉 OrderDTO
	}
	*/
	
	// 聯集(@OneToMany(cascade = CascadeType.ALL)
	@Override
	public Optional<OrderDTO> saveOrder(Long userId, List<OrderItemDTO> orderItems) {
		// 1. 得到 user
		Optional<User> optUser = userRepository.findById(userId);
		if(optUser.isEmpty()) return null;
		User user = optUser.get();
		
		// 2. 建立訂單
		Order order = new Order();
		order.setUser(user); // 設定關聯
		
		// 3. 建立訂單細目
		List<OrderItem> items = orderItems.stream() // ...OrderItem
						.map(orderItemDTO -> { 
							OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
							orderItem.setOrder(order);
							return orderItem;
						}) // ...orderItemDTO
						.toList(); // List<OrderItem>
		
		// 4. 儲存訂單
		order.setOrderItems(items);
		orderRepository.save(order);
		
		return Optional.of(modelMapper.map(order, OrderDTO.class)); // Order 轉 OrderDTO
	}

}
