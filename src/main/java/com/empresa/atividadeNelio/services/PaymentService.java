package com.empresa.atividadeNelio.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.empresa.atividadeNelio.dto.PaymentDTO;
import com.empresa.atividadeNelio.entities.Order;
import com.empresa.atividadeNelio.entities.Payment;
import com.empresa.atividadeNelio.repositories.OrderRepository;
import com.empresa.atividadeNelio.repositories.PaymentRepository;
import com.empresa.atividadeNelio.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {
	
	@Autowired
	private PaymentRepository repository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<PaymentDTO> findAll(){
		List<Payment> list= repository.findAll();
		return list.stream().map(e -> new PaymentDTO(e)).collect(Collectors.toList());
	}
	public PaymentDTO findById(Long id) {
		
		Optional<Payment> obj= repository.findById(id);
		Payment entity= obj.orElseThrow(() -> new ResourceNotFoundException(id));
		return new PaymentDTO(entity);
	}
	
	@Transactional
	public PaymentDTO insert(PaymentDTO obj) {
		Order order= orderRepository.getOne(obj.getOrderId());
		Payment payment= new Payment(null,obj.getMoment(),order);
		order.setPayment(payment);
		orderRepository.save(order);
		return new PaymentDTO(order.getPayment());
			
	}
	
	
	@Transactional
	public PaymentDTO update(Long id, PaymentDTO dto) {
		try {
		Payment entity= repository.getOne(id);
		updateData(entity, dto);
		entity= repository.save(entity);
		return new PaymentDTO(entity);
		
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
		
	
	}
	private void updateData(Payment entity, PaymentDTO dto) {
		// TODO Auto-generated method stub
		entity.setMoment(dto.getMoment());
		
	}

}
