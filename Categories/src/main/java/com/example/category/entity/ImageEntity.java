package com.example.category.entity;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
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
@Table(name = "image_entity")
public class ImageEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long imageId;
	
	private String name;
	
	private String type;
	
	@Column(length = 50000000)
	private byte[] imageByte;
	
  @ManyToMany(mappedBy = "productImages",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private Set<Product> products;
	
  @ManyToMany(mappedBy = "categoryImages",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
 	private Set<Category> category;
	
	public ImageEntity(String name, String type, byte[] imageByte)
	{
		this.name = name;
		this.type = type;
		this.imageByte = imageByte;
	}
	
}
