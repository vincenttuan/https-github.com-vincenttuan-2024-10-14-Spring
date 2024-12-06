package com.example.mcdonalds.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mcdonalds.model.dto.ProductDTO;
import com.example.mcdonalds.response.ApiResponse;
import com.example.mcdonalds.service.ProductService;

/*
 * WEB REST API
 * ----------------------------------
 * Servlet-Path: /products
 * ----------------------------------
 * GET   /products      查詢所有商品(多筆)
 * GET   /products/{id}  查詢指定商品(單筆)
 * POST  /products      新增商品
 * */
@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	// 查詢所有商品(多筆)
	@GetMapping
	public ResponseEntity<ApiResponse<List<ProductDTO>>> getAllProducts() {
		return ResponseEntity.ok(ApiResponse.success("查詢成功", productService.getAllProducts()));
	}
	
	// 查詢指定商品(單筆)
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse<ProductDTO>> getProduct(@PathVariable Long id) {
		Optional<ProductDTO> optProductDTO = productService.getProductById(id);
		if(optProductDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "查無此商品"));
		}
		return ResponseEntity.ok(ApiResponse.success("查詢成功", optProductDTO.get()));
	}
	
	// 新增商品
	@PostMapping
	public ResponseEntity<ApiResponse<ProductDTO>> addProduct(@RequestBody ProductDTO productDTO) {
		Optional<ProductDTO> optProductDTO = productService.saveProduct(productDTO);
		if(optProductDTO.isEmpty()) {
			return ResponseEntity.status(404).body(ApiResponse.error(404, "新增失敗"));
		}
		return ResponseEntity.ok(ApiResponse.success("新增成功", optProductDTO.get()));
	}
}










