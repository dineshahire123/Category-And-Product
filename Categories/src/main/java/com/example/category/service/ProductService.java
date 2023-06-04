package com.example.category.service;

import org.springframework.stereotype.Service;

import com.example.category.entity.Product;

@Service
public interface ProductService {

	Product addProducts(Product product);

	Product getByName(String name);

	Product updateProduct(String name, Product product);

	String deletByName(String name);

}
