package com.example.mcdonalds.service.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mcdonalds.model.dto.ProductDTO;
import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.ProductImage;
import com.example.mcdonalds.repository.ProductRepository;
import com.example.mcdonalds.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public List<ProductDTO> getAllProducts() {
		return productRepository.findAll()  // List<Product>
								.stream()   // ...Product
								.map(product -> modelMapper.map(product, ProductDTO.class)) // ...ProductDTO
								.toList();
	}							

	@Override
	public Optional<ProductDTO> getProductById(Long id) {
		Optional<Product> optProduct = productRepository.findById(id);
		if(optProduct.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(modelMapper.map(optProduct.get(), ProductDTO.class));
	}

	@Override
	public Optional<ProductDTO> saveProduct(ProductDTO productDTO) {
		// 1. ProductDTO 轉 Product
		Product product = modelMapper.map(productDTO, Product.class);
		
		// 2. 配置 ProductImage
		ProductImage productImage = new ProductImage();
		productImage.setImageBase64(productDTO.getImageBase64());
		product.setProductImage(productImage);
		
		// 3. 儲存
		productRepository.save(product);
		
		return Optional.of(modelMapper.map(product, ProductDTO.class));
	}

}
