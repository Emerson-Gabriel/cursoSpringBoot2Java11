package com.empresa.atividadeNelio.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.atividadeNelio.dto.ProductDTO;
import com.empresa.atividadeNelio.dto.UserDTO;
import com.empresa.atividadeNelio.entities.Product;
import com.empresa.atividadeNelio.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired 
	private ProductService service;
	
	@GetMapping
	public ResponseEntity<List<ProductDTO>> findAll() {
		List<ProductDTO> list = service.findAll();
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ProductDTO> findById(@PathVariable Long id) {
		ProductDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
}
