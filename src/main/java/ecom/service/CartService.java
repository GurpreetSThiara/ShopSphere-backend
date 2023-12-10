package ecom.service;

import ecom.exception.ProductException;
import ecom.modal.Cart;
import ecom.modal.User;
import ecom.request.AddItemRequest;

public interface CartService {
	
	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;
	
	public Cart findUserCart(Long userId);

}
