package com.ecom.service;

import com.ecom.exception.ProductException;
import com.ecom.modal.Cart;
import com.ecom.modal.User;
import com.ecom.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
