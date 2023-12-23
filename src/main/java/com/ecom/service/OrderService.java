package com.ecom.service;



import com.ecom.exception.OrderException;
import com.ecom.modal.Address;
import com.ecom.modal.Order;
import com.ecom.modal.User;

import java.util.List;

public interface OrderService {
	
	public Order createOrder(User user, Address shippingAdress);
	
	public  Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId)throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order cancledOrder(Long orderId) throws OrderException;
	
	public List<Order>getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;
	
}
