package com.example.mcdonalds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.User;
import com.example.mcdonalds.repository.OrderItemRepository;
import com.example.mcdonalds.repository.OrderRepository;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;

@SpringBootTest
public class OrderTest {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void addOrder() {
		// Mary 要下一筆訂單, 訂單內容: 大麥克 1 個, 麥香魚 1 個
		// 取得 mary(id=2) 的 user 物件
		User mary = userRepository.findById(2L).get();
		// 取得 大麥克(id=2) 的 product 物件
		Product bigMac = productRepository.findById(2L).get();
		// 取得 麥香魚(id=1) 的 product 物件
		Product fish = productRepository.findById(1L).get();
		
		// 建立訂單
		
	}
}
