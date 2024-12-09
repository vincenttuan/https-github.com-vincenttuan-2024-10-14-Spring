package com.example.mcdonalds;

import static org.mockito.Mockito.when;

import java.util.Iterator;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.User;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;

@SpringBootTest
public class FavoriteRemoveTest {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void removeFavorite() {
		// Mary 想要移除麥香魚商品
		User mary = userRepository.findById(2L).get();
		
		Product fish = productRepository.findById(1L).get();
		
		//mary.getFavoriteProducts().remove(fish);   // 取得關聯關係並加入對 product 的關注
		Iterator<Product> iter = mary.getFavoriteProducts().iterator();
		while(iter.hasNext()) {
			Product p = iter.next();
			if(p.getId().equals(fish.getId())) {
				iter.remove();
			}
		}
		
		userRepository.save(mary); // 保存關聯關係
		System.out.println("關注筆數: " + mary.getFavoriteProducts().size());
	}
}
