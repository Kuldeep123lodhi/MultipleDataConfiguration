package com.multiple.datasource.configuration.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multiple.datasource.configuration.entity.Product;
import com.multiple.datasource.configuration.product.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<Product> getProduct(){
		
		return productRepository.findAll();
	}

}
