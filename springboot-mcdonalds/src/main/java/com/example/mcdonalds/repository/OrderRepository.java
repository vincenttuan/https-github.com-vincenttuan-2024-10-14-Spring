package com.example.mcdonalds.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mcdonalds.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	// 取得該用戶的訂單紀錄
	List<Order> findByUserId(Long userId);
}
