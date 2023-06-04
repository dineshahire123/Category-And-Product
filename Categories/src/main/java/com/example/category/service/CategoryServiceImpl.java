package com.example.category.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.category.entity.Category;
import com.example.category.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category addCategory(Category category) {
		// TODO Auto-generated method stub
		
	   Category category1 = categoryRepository.save(category);
		  
	   return category1;
		
		
	}

	@Override
	public Category getCategoryByName(String name) {
		// TODO Auto-generated method stub
		
	  Category category1 =	categoryRepository.findByName(name);
		
		return category1;
	}

	@Override
	public Category updateCategory(String name, Category category) {
		// TODO Auto-generated method stub
		
		Category category1 = getCategoryByName(name);
		
		category1.setName(category.getName());
		category1.setProduct(category.getProduct());
		
		return category1;
	}

	@Override
	public String deleteCategory(String name, Category category) {
		// TODO Auto-generated method stub
		
               Category category1 = getCategoryByName(name);
		
               categoryRepository.delete(category1);
		return "Category Deleted Successfully !!";
	}

}
