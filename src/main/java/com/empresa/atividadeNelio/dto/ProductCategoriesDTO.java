package com.empresa.atividadeNelio.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.empresa.atividadeNelio.entities.Product;

public class ProductCategoriesDTO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private Double price;
	private String imgUrl;
	
	private List<CategoryDTO> categories = new ArrayList<CategoryDTO>();
	
	public ProductCategoriesDTO() {
		
	}

	public ProductCategoriesDTO(String name, String description, Double price, String imgUrl) {
		
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUrl = imgUrl;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public List<CategoryDTO> getCategories() {
		return categories;
	}


	public ProductCategoriesDTO(Product entity) {
		this.name = entity.getName();
		this.imgUrl = entity.getImgUrl();
		this.price = entity.getPrice();
		this.description = entity.getDescription();
	}
	
	public Product toEntity() {
		return new Product(null, name, description, price, imgUrl);
	}
	
}
