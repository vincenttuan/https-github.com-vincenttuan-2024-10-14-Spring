package com.example.mcdonalds.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mcdonalds.model.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
