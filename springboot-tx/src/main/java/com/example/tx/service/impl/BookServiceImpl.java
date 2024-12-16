package com.example.tx.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tx.repository.BookInventoryRepository;
import com.example.tx.repository.BookRepository;
import com.example.tx.repository.WalletRepository;
import com.example.tx.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private BookInventoryRepository bookInventoryRepository;
	
	@Autowired
	private WalletRepository walletRepository;
	
	// 書本價格
	@Override
	public Integer getBookPrice(Integer bookId) {
		return bookRepository.getBookPrice(bookId);
	}
	
	// 書本庫存
	@Override
	public Integer getBookAmount(Integer bookId) {
		return bookInventoryRepository.getBookAmount(bookId);
	}
	
	// 帳戶餘額
	@Override
	public Integer getWalletBalance(String username) {
		return walletRepository.getWalletBalance(username);
	}
	
	// 更新庫存
	@Override
	public void reduceBookAmount(Integer bookId, Integer amountToReduce) {
		// 1. 取得目前的庫存
		Integer bookAmount = getBookAmount(bookId);
		// 2. 若目前的庫存小於 amountToReduce 就要拋出例外
		if(bookAmount < amountToReduce) {
			throw new RuntimeException("庫存不足");
		}
		// 3. 更新庫存
		bookInventoryRepository.updateBookAmount(amountToReduce, bookId);
	}
	
	// 更新餘額
	@Override
	public void updateWalletBalacen(String username, Integer bookPrice) {
		// 1. 取得目前的餘額
		Integer walletBalance = getWalletBalance(username);
		// 2. 若目前的餘額小於 bookPrice 就要拋出例外
		if(walletBalance < bookPrice) {
			throw new RuntimeException("餘額不足");
		}
		// 3. 更新餘額
		walletRepository.updateWalletBalance(bookPrice, username);
	}

}
