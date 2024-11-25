package com.example.mcdonalds.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mcdonalds.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	// 根據 username 尋找 user
	Optional<User> findByUsername(String username);
}
