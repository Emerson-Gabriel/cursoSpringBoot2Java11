package com.empresa.atividadeNelio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.atividadeNelio.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
}
