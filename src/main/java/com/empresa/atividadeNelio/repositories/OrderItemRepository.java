package com.empresa.atividadeNelio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.empresa.atividadeNelio.entities.OrderItem;
import com.empresa.atividadeNelio.entities.OrderItemPK;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {

}
