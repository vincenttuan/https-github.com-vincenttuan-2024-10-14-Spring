package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tx.service.BookService;
import com.example.tx.service.BuyService;

import jakarta.transaction.Transactional;

@Service // 交易服務
public class BuyServiceImpl implements BuyService {
	
	@Autowired
	private BookService bookService;
	
	@Transactional
	@Override
	public void buyOneBook(String username, Integer bookId) {
		System.out.println(username + " 要買一本書, bookId = " + bookId);
		// 1. 查詢書本價格 - select table
		Integer bookPrice = bookService.getBookPrice(bookId);
		System.out.println("bookId = " + bookId + " 價格 = " + bookPrice);
		
		// 2. 減去庫存(1本) - update book_inventory table
		bookService.reduceBookAmount(bookId, 1);
		System.out.println("bookId = " + bookId + ", 減去一本書的庫存");
		
		// 3. 修改餘額(減去一本書的價格) - update wallet table 
		bookService.updateWalletBalacen(username, bookPrice);
		
		// 4. 其他處理
		System.out.println(username + " 結帳完成 ...");
	}
	
}
