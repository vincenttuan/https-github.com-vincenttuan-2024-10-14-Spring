package com.example.mcdonalds;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mcdonalds.model.entity.Product;
import com.example.mcdonalds.model.entity.ProductImage;
import com.example.mcdonalds.repository.ProductImageRepository;
import com.example.mcdonalds.repository.ProductRepository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;

@SpringBootTest
public class ProductTest {
	@Autowired
	ProductImageRepository productImageRepository;
	
	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void addProduct() {
		// case 1: 無聯級(Cascade 連動)設定
		/*
		// 1. 先建立 product_image 資料並儲存
		ProductImage productImage = new ProductImage();
		productImage.setImageBase64("abcd1234"); // 設定資料
		productImageRepository.save(productImage); // 儲存
		
		// 2. 在建立 product + 關聯配置並儲存
		Product product = new Product();
		product.setName("麥香魚"); // 設定資料
		product.setPrice(60); // 設定資料
		product.setProductImage(productImage); // 設定關聯資料
		productRepository.save(product); // 儲存
		*/
		
		// case 2: 有聯級(Cascade 連動)設定
		// 例如: @OneToOne(cascade = CascadeType.ALL)
		// 1. 先建立 product_image 資料, 但是不用先儲存
		ProductImage productImage = new ProductImage();
		productImage.setImageBase64("1234abcdef"); // 設定資料
		
		// 2. 在建立 product + 關聯配置並儲存
		Product product = new Product();
		product.setName("大麥克"); // 設定資料
		product.setPrice(78); // 設定資料
		product.setProductImage(productImage); // 設定關聯資料
		productRepository.save(product); // 儲存時會連同 productImage 也一起儲存
	}
}
