package com.example.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.category.entity.Product;
import com.example.category.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired  
	private ProductRepository productRepository;
	
	@Override
	public Product addProducts(Product product) {
		// TODO Auto-generated method stub
		
		Product product2 = productRepository.save(product);
		
		return product2;
	}

	@Override
	public Product getByName(String name) {
		// TODO Auto-generated method stub
		
	 Product product1 =	productRepository.findByName(name);
		
		
		return product1;
	}

	@Override
	public Product updateProduct(String name, Product product) {
		// TODO Auto-generated method stub
		
		   Product product1  = getByName(name);
		
		  product1.setDescription(product.getDescription());
		  product1.setName(product.getName());
		  product1.setCategory(product.getCategory());
		  product1.setProductImages(product.getProductImages());
		  product1.setProductUse(product.getProductUse());
		  product1.setQuantity(product.getQuantity());
		  product1.setStock(product.getStock());
		
		return product1;
	}

	@Override
	public String deletByName(String name) {
		// TODO Auto-generated method stub
		
		
		  Product product1 =  getByName(name);
		    
	      productRepository.delete(product1);       
		
		return "Product Deleted Successfully";
	}

}
