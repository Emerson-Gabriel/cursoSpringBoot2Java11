package com.empresa.atividadeNelio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.atividadeNelio.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
