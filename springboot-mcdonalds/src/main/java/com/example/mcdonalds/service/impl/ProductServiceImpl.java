package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.ProductDTO;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	
	@Override
	public List<ProductDTO> getAllProducts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<ProductDTO> saveProduct(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
