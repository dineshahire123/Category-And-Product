package com.example.category.service;

import org.springframework.stereotype.Service;

import com.example.category.entity.Category;

@Service
public interface CategoryService {

	Category addCategory(Category category);

	Category getCategoryByName(String name);

	Category updateCategory(String name, Category category);

	String deleteCategory(String name, Category category);

}
