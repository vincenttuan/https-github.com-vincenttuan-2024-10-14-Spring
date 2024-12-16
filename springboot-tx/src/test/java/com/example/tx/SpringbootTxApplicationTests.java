package com.example.tx;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootTxApplicationTests {

	@Test
	void contextLoads() {
		
		// 俄羅斯輪盤
		List<Integer> queue = List.of(1, 2, 4, 6, 8, 9, 12, 14, 16, 17, 28);
		
		Set<Integer> lucky = new LinkedHashSet<>();
		
		while (lucky.size() < 3) {
			int idx = new Random().nextInt(queue.size());
			lucky.add(queue.get(idx));
		}
		
		System.out.println("幸運者:" + lucky);
		
	}

}
