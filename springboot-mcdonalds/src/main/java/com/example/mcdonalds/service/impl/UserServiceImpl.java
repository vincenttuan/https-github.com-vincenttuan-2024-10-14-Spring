package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.exception.ProductNotFoundException;
import com.example.mcdonalds.exception.UserNotFoundException;
import com.example.mcdonalds.model.dto.FavoriteProductDTO;
import com.example.mcdonalds.model.dto.FavoriteUserDTO;
import com.example.mcdonalds.model.dto.LoginDTO;
import com.example.mcdonalds.model.dto.UserDTO;
import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.User;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.repository.UserRepository;
import com.example.mcdonalds.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public Optional<UserDTO> findByUsername(String username) {
		Optional<User> optUser = userRepository.findByUsername(username);
		if(optUser.isEmpty()) {
			return Optional.empty();
		}
		// 利用 modelMapper 將 entity 轉 DTO
		UserDTO userDTO = modelMapper.map(optUser.get(), UserDTO.class);
		return Optional.of(userDTO);
	}
	
	/*
	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		// 1. 透過 username 找到 user
		Optional<User> optUser = userRepository.findByUsername(loginDTO.getUsername());
		if(optUser.isEmpty()) {
			return Optional.empty();
		}
		// 2. 判斷密碼
		User user = optUser.get();
		if(user.getPassword().equals(loginDTO.getPassword())) {
			// 3. entity 轉 DTO
			UserDTO userDTO = modelMapper.map(user, UserDTO.class);
			return Optional.of(userDTO);
		}
		return Optional.empty();
	}
	*/
	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		// 1. 透過 username 找到 user
		Optional<User> optUser = userRepository.findByUsername(loginDTO.getUsername());
		// 2. 判斷密碼
		if(optUser.isPresent() && optUser.get().getPassword().equals(loginDTO.getUsername())) {
			return Optional.of(modelMapper.map(optUser.getClass(), UserDTO.class));
		}
		return Optional.empty();
	
	}

	@Override
	public Optional<UserDTO> saveUser(UserDTO userDTO) {
		
		//User user = modelMapper.map(userDTO, User.class);
		//user = userRepository.save(user);
		
		User user = userRepository.save(modelMapper.map(userDTO, User.class));
		
		return Optional.of(modelMapper.map(user, UserDTO.class));
	}
	
	// 用戶關注列表(用戶關注那些商品)
	@Override
	public List<FavoriteProductDTO> getFavoriteProducts(Long userId) {
		// 1. 找到 user, 若沒找到則拋出例外
		User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException());
		// 2. 該用戶的商品集合
		Set<Product> products = user.getFavoriteProducts();
		// 3. 將 products 中的每一個 product 元素轉成 FavoriteProductDTO
		return products // Set<Product>
					.stream() // ... Product
					.map(product -> modelMapper.map(product, FavoriteProductDTO.class)) // ... FavoriteProductDTO
					.toList(); // List<FavoriteProductDTO>
	}

	@Override
	public List<FavoriteUserDTO> getFavoriteUsers(Long productId) {
		// 1. 找到 product, 若沒找到則拋出例外
		 Product product = productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException());
		// 2. 該商品的用戶集合(被那些用戶關注)
		 Set<User> users = product.getFavoriteUsers();
		// 3. 將 users 中的每一個 user 元素轉成 UserDTO
		return users  // Set<User>
					.stream() // ... user
					.map(user -> modelMapper.map(user, FavoriteUserDTO.class)) // ... FavoriteUserDTO
					.toList(); // List<FavoriteUserDTO>
	}

	@Override
	public void addFavoriteProduct(Long userId, Long productId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeFavoriteProduct(Long userId, Long productId) {
		// TODO Auto-generated method stub
		
	}

}
