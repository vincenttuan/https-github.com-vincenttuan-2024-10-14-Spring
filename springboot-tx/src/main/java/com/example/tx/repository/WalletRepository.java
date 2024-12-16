package com.example.tx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tx.entity.Wallet;

import jakarta.transaction.Transactional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
	// 取得客戶餘額
	@Query(value = "select balance from wallet where username = :username", nativeQuery = true)
	Integer getWalletBalance(String username);
	
	// 更新客戶餘額(目前餘額 - bookPrice)
	@Modifying
	@Transactional
	@Query(value = "update wallet set balance = balance - :bookPrice where username = :username", nativeQuery = true)
	void updateWalletBalance(Integer bookPrice, String username);
	
}
