package com.empresa.atividadeNelio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.atividadeNelio.entities.Order;
import com.empresa.atividadeNelio.entities.User;

public interface OrderRepository extends JpaRepository<Order, Long> {

	List<Order> findByClient(User client);
}
