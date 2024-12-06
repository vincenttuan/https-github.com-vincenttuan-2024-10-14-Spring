package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.FavoriteProductDTO;
import com.example.mcdonalds.model.dto.FavoriteUserDTO;
import com.example.mcdonalds.model.dto.LoginDTO;
import com.example.mcdonalds.model.dto.UserDTO;
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
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserDTO> login(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<UserDTO> saveUser(UserDTO userDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<FavoriteProductDTO> getFavoriteProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FavoriteUserDTO> getFavoriteUsers() {
		// TODO Auto-generated method stub
		return null;
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
