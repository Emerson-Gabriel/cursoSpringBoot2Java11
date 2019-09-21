package com.empresa.atividadeNelio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.empresa.atividadeNelio.dto.CategoryDTO;
import com.empresa.atividadeNelio.dto.ProductCategoriesDTO;
import com.empresa.atividadeNelio.dto.ProductDTO;
import com.empresa.atividadeNelio.entities.Category;
import com.empresa.atividadeNelio.entities.Product;
import com.empresa.atividadeNelio.repositories.CategoryRepository;
import com.empresa.atividadeNelio.repositories.ProductRepository;
import com.empresa.atividadeNelio.services.exceptions.DatabaseException;
import com.empresa.atividadeNelio.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<ProductDTO> findAll() {
		List<Product> list = repository.findAll();
		return list.stream().map(e -> new ProductDTO(e)).collect(Collectors.toList());
	}
	
	public ProductDTO findById(Long id) {
		Optional<Product> obj = repository.findById(id);
		Product entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO insert(ProductCategoriesDTO dto) {
		Product entity = dto.toEntity();
		setProductCategories(entity, dto.getCategories());
		entity = repository.save(entity);
		return new ProductDTO(entity);
		
	}

	private void setProductCategories(Product entity, List<CategoryDTO> categories) {
		// asseguramos que esse produc nao tem cat
		entity.getCategories().clear();
		for (CategoryDTO dto : categories) {
			Category category = categoryRepository.getOne(dto.getId());
			entity.getCategories().add(category);
		}
	}
	
	
	@Transactional
	public ProductDTO update(Long id, ProductCategoriesDTO dto) {
		try {
			
			Product entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new ProductDTO(entity);
			
		} catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);	
		}
	}
	
	public void updateData(Product entity, ProductCategoriesDTO dto) {
		
		entity.setName(dto.getName());
		entity.setDescription(dto.getDescription());
		entity.setPrice(dto.getPrice());
		entity.setImgUrl(dto.getImgUrl());
		if (dto.getCategories() != null && dto.getCategories().size() >0) {
			//validacao para atualizae as cate someente quando mudar
			setProductCategories(entity, dto.getCategories());
		}
	}
	
	public void delete(Long id) {
		
		try {
			repository.deleteById(id);
		}catch (EmptyResultDataAccessException e) {
			// TODO: handle exception
			throw new ResourceNotFoundException(id);			
		}catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());		
		}
		
	}
	
}
