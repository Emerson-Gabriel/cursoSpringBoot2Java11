package com.empresa.atividadeNelio.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.empresa.atividadeNelio.entities.Category;
import com.empresa.atividadeNelio.repositories.CategoryRepository;
import com.empresa.atividadeNelio.services.exceptions.DatabaseException;
import com.empresa.atividadeNelio.services.exceptions.ResourceNotFoundException;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repository;
	
	public List<Category> findAll(){
		return repository.findAll();
	}
	
	public Category findById(Long id) {
		Optional<Category> obj = repository.findById(id);
		return obj.get();
	}
	
	public Category insert(Category obj) {
		return repository.save(obj);
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
	
	public Category update(Long id, Category obj) {
		try {
			Category entity = repository.getOne(id);
			updateData(entity, obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);	
		}
	}
	
	public void updateData(Category entity, Category obj) {
		entity.setName(obj.getName());
		
	}
	
}
