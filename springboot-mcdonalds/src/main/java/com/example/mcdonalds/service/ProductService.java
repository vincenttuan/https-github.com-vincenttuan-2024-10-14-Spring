package com.example.mcdonalds.service;

import java.util.List;
import java.util.Optional;

import com.example.mcdonalds.model.dto.ProductDTO;

/*
 * 功能:
 * 1.取得所有商品
 * 2.取得指定商品
 * 3.新增/儲存商品
 * */
public interface ProductService {
	// 取得所有商品
	List<ProductDTO> getAllProducts();
	
	// 取得指定商品(根據 id)
	Optional<ProductDTO> getProductById(Long id);
	
	// 新增/儲存商品
	Optional<ProductDTO> saveProduct(ProductDTO productDTO);
}
