package com.example.mcdonalds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.User;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;

@SpringBootTest
public class FavoriteTest {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void addFavorite() {
		// Mary 想要關注 麥香魚與大麥克 商品
		User mary = userRepository.findById(2L).get();
		
		Product fish = productRepository.findById(1L).get();
		Product bigMac = productRepository.findById(2L).get();
		
		mary.getFavoriteProducts().add(fish);   // 取得關聯關係並加入對 product 的關注 
		mary.getFavoriteProducts().add(bigMac); // 取得關聯關係並加入對 product 的關注
		
		userRepository.save(mary); // 保存關聯關係
	}
}
