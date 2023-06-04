package com.example.category.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int productId;
   
	@Column(name = "name")
	private String name;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "productUse")
	private String productUse;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "stock")
	private long stock;
	
	@ManyToOne
	private Category category;

	@ManyToMany
	   @JoinTable(name = "product_images",
	    
	    joinColumns = {
	    		@JoinColumn(name = "product_id") 
	    },
	    
	    inverseJoinColumns = {
	    		@JoinColumn(name = "image_id")
	    }

			)
  	private Set<ImageEntity> productImages;  
   
    
}
