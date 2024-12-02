package com.example.mcdonalds.service;

import java.util.List;
import java.util.Optional;

import com.example.mcdonalds.model.dto.FavoriteProductDTO;
import com.example.mcdonalds.model.dto.FavoriteUserDTO;
import com.example.mcdonalds.model.dto.LoginDTO;
import com.example.mcdonalds.model.dto.UserDTO;

/*
 * 功能:
 * 1.根據帳號來查詢使用者
 * 2.登入 login
 * 3.新增/儲存使用者
 * 4.使用者關注列表(使用者關注那些商品)
 * 5.商品關注列表(商品被那些使用者關注)
 * 6.新增商品關注
 * 7.移除商品關注
 * */
public interface UserService {
	// 1.根據帳號來查詢使用者
	Optional<UserDTO> findByUsername(String username);

	// 2.登入 login
	Optional<UserDTO> login(LoginDTO loginDTO);
	
	// 3.新增/儲存使用者
	Optional<UserDTO> saveUser(UserDTO userDTO);
	
	// 4.使用者關注列表(使用者關注那些商品)
	List<FavoriteProductDTO> getFavoriteProducts();
	
	// 5.商品關注列表(商品被那些使用者關注)
	List<FavoriteUserDTO> getFavoriteUsers();
	
	// 6.新增商品關注
	void addFavoriteProduct(Long userId, Long productId);
	
	// 7.移除商品關注
	void removeFavoriteProduct(Long userId, Long productId);
}
