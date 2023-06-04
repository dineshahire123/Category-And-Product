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

import com.example.category.entity.ImageEntity;
import com.example.category.entity.Product;
import com.example.category.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
   
  @Autowired
  private ProductService productService;
  
  
  @PostMapping(value = {"/save"} , consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  public ResponseEntity<Product> addProducts(@RequestPart("product") Product product, @RequestPart("imageFile") MultipartFile[] file)
  {
//	  Product product1 = productService.addProducts(product);
//  
//	  return null;
	   
	  try {
		 Set<ImageEntity> images =  uploadImage(file);
		  product.setProductImages(images);
		  
		  Product product1 = productService.addProducts(product);
		  return new ResponseEntity<Product>(product1,HttpStatus.ACCEPTED);
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
  public ResponseEntity<Product> getByName(@PathVariable("name") String name, @RequestBody Product product)
  {
	  
	  Product product1 =  productService.getByName(name);
	  
	  return new ResponseEntity<Product>(product1,HttpStatus.ACCEPTED);
  }
  
  
  @PutMapping("/update/{name}")
  public ResponseEntity<Product> updateByName(@PathVariable("name") String name, @RequestPart Product product,@RequestPart MultipartFile[] file)
  {
	  try {
		 Set<ImageEntity> images =  uploadImage(file);
		  product.setProductImages(images);
		  
		  Product product1 = productService.updateProduct(name,product);
		  return new ResponseEntity<Product>(product1,HttpStatus.ACCEPTED);
	  }catch(Exception e)
	  {
		  System.out.println(e.getMessage());
		  return null;
	  }
  }
  
  
  @DeleteMapping("/delete/{name}")
  public String deleteByName(@PathVariable("name") String name)
  {
	  String message =  productService.deletByName(name);
	  
	  return message;
  }
  
  
  
  
}
