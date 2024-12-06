package com.example.mcdonalds;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mcdonalds.model.entity.Order;
import com.example.mcdonalds.model.entity.OrderItem;
import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.User;
import com.example.mcdonalds.repository.OrderItemRepository;
import com.example.mcdonalds.repository.OrderRepository;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class OrderFindTest {
	@Autowired
	OrderRepository orderRepository;
	
	@Test
	public void findOrder() {
		List<Order> orders = orderRepository.findByUserId(2L);
		orders.get(0).getOrderItems().size();
		System.out.println(orders);
	}
}
