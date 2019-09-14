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

import com.empresa.atividadeNelio.dto.UserDTO;
import com.empresa.atividadeNelio.dto.UserInsertDTO;
import com.empresa.atividadeNelio.entities.User;
import com.empresa.atividadeNelio.repositories.UserRepository;
import com.empresa.atividadeNelio.services.exceptions.DatabaseException;
import com.empresa.atividadeNelio.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> list = repository.findAll();
		return list.stream().map(e -> new UserDTO(e)).collect(Collectors.toList());
	}
	
	public UserDTO findById(Long id) {
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new UserDTO(entity);
	}
	
	public UserDTO insert(UserInsertDTO dto) {
		User entity = dto.toEntity();
		
		entity = repository.save(entity);
		return new UserDTO(entity);
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
	
	
	@Transactional
	public UserDTO update(Long id, UserDTO dto) {
		try {
			
			User entity = repository.getOne(id);
			updateData(entity, dto);
			entity = repository.save(entity);
			return new UserDTO(entity);
			
		} catch (EntityNotFoundException e){
			throw new ResourceNotFoundException(id);	
		}
	}
	
	public void updateData(User entity, UserDTO dto) {
		entity.setName(dto.getName());
		entity.setEmail(dto.getEmail());
		entity.setPhone(dto.getPhone());
		
	}
	
}
