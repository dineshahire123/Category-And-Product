package com.example.category.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.category.entity.Category;
import com.example.category.entity.ImageEntity;
import com.example.category.entity.Product;
import com.example.category.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	 @PostMapping(value = {"/save"} , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	  public ResponseEntity<Category> addProducts(@RequestPart("category") Category category, @RequestPart("imageFile") MultipartFile[] file)
	  {		   
		  try {
			 Set<ImageEntity> images =  uploadImage(file);
			  category.setCategoryImages(images);
			  
			  Category category1 = categoryService.addCategory(category);
			  return new ResponseEntity<Category>(category1,HttpStatus.ACCEPTED);
		  }catch(Exception e)
		  {
			  System.out.println(e.getMessage());
			  return null;
		  }
		  
		
	  }
	  
	  
	  
	  public Set<ImageEntity> uploadImage(MultipartFile[] multipartFiles) throws IOException
	  {
		  
		  Set<ImageEntity> imageEntity = new HashSet<>();
		  
		  
		  for(MultipartFile file : multipartFiles) {
			  
			   ImageEntity imageEntity1 = new ImageEntity(
					   
					   file.getOriginalFilename(),
					   file.getContentType(),
					   file.getBytes()
					   );
					 
			  imageEntity.add(imageEntity1);
		    
		  }
		  return imageEntity;		  
	  }
	  
	  
	@GetMapping("/get/{name}")
	public ResponseEntity<Category> getProductByName(@PathVariable("name") String name)
	{
		Category category1 = categoryService.getCategoryByName(name);
		
		return new ResponseEntity<Category>(category1,HttpStatus.OK);
		
	}
	
	
	
	@PutMapping(value = "upadte/{name}" , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<Category> updateByName(@PathVariable("name") String name, @RequestPart Category category,  @RequestPart("imageFile") MultipartFile[] file)
	{
		 try {
			 Set<ImageEntity> images =  uploadImage(file);
			  category.setCategoryImages(images);
			  
			  Category category1 = categoryService.updateCategory(name,category);
			  return new ResponseEntity<Category>(category1,HttpStatus.ACCEPTED);
		  }catch(Exception e)
		  {
			  System.out.println(e.getMessage());
			  return null;
		  }
	}
	
	
	@DeleteMapping("/delete/{name}")
	public String deleteCategory(@PathVariable("name") String name, @RequestBody Category category)
	{
		String message = categoryService.deleteCategory(name,category);
		
		return message;
	}
}


