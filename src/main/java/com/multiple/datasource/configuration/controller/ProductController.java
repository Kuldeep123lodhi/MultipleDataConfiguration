package com.multiple.datasource.configuration.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.multiple.datasource.configuration.entity.Product;
import com.multiple.datasource.configuration.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService serviceProduct;
	
   @GetMapping
	public List<Product> getProduct(){
		
		return serviceProduct.getProduct();
	}
	
}
