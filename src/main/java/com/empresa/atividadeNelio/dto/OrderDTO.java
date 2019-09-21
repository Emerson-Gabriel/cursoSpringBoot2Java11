package com.empresa.atividadeNelio.dto;

import java.io.Serializable;
import java.time.Instant;

import com.empresa.atividadeNelio.entities.Order;
import com.empresa.atividadeNelio.entities.User;
import com.empresa.atividadeNelio.entities.enums.OrderStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

public class OrderDTO implements Serializable{

	
	
	private static final long serialVersionUID = 1L;
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant moment;
	private Long clientID;
	private OrderStatus orderStatus;
	private String clientName;
	private String clientEmail;
	
	public OrderDTO() {
		
	}

	public OrderDTO(Long id, Instant moment, Long clientID, OrderStatus orderStatus, String clientName,
			String clientEmail) {


		this.id = id;
		this.moment = moment;
		this.clientID = clientID;
		this.orderStatus = orderStatus;
		this.clientName = clientName;
		this.clientEmail = clientEmail;
	}
	

	/**
	 * @param entity
	 */
	public OrderDTO(Order entity) {
		if (entity.getClient() == null) {
			throw new IllegalArgumentException("Erro instant OrderDTO: client was null");
		}
		
		this.id = entity.getId();
		this.moment = entity.getMoment();
		this.clientID = entity.getClient().getId();
		this.orderStatus = entity.getOrderStatus();
		this.clientName = entity.getClient().getName();
		this.clientEmail = entity.getClient().getEmail();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getMoment() {
		return moment;
	}

	public void setMoment(Instant moment) {
		this.moment = moment;
	}

	public Long getClientID() {
		return clientID;
	}

	public void setClientID(Long clientID) {
		this.clientID = clientID;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}
	
	public Order toEntity() {
		User client = new User(clientID, clientName, clientEmail, null, null);
		return new Order(id, moment,  orderStatus, client);
	}
	
}
