package com.empresa.atividadeNelio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.atividadeNelio.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
	
}
