package com.example.tx;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tx.service.BuyService;

@SpringBootTest
public class TestBuyOneBook {
	@Autowired
	private BuyService buyService;
	
	@Test
	public void test() {
		try {
			buyService.buyOneBook("John", 1);
			System.out.println("買書成功");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			System.err.println("買書失敗");
		}
	}
}
