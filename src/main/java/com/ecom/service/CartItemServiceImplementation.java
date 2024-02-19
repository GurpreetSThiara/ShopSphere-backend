package com.ecom.service;

import java.time.LocalDateTime;
import java.util.Optional;

import com.ecom.exception.CartItemException;
import com.ecom.exception.UserException;
import com.ecom.modal.*;
import com.ecom.repository.CartItemRepository;
import com.ecom.repository.CartRepository;
import com.ecom.repository.UserInteractionRepository;
import com.ecom.user.domain.UserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemServiceImplementation implements CartItemService {
	
	private CartItemRepository cartItemRepository;
	private UserService userService;
	private CartRepository cartRepository;

	@Autowired
	private UserInteractionRepository userInteractionRepository;
	
	public CartItemServiceImplementation(CartItemRepository cartItemRepository,UserService userService) {
		this.cartItemRepository=cartItemRepository;
		this.userService=userService;
	}

	@Override
	public CartItem createCartItem(CartItem cartItem) {
		
		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice()*cartItem.getQuantity());
		cartItem.setDiscountedPrice(cartItem.getProduct().getDiscountedPrice()*cartItem.getQuantity());
		
		CartItem createdCartItem=cartItemRepository.save(cartItem);

		UserInteraction userInteraction = new UserInteraction();
		userInteraction.setUserId(createdCartItem.getUserId());
		userInteraction.setSellerId(createdCartItem.getProduct().getSellerShopId());
		userInteraction.setTimestamp(LocalDateTime.now());
		userInteraction.setAction(UserActions.ADD_TO_CART);
		userInteraction.setProductId(createdCartItem.getProduct().getId());
		userInteractionRepository.save(userInteraction);
		
		return createdCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {
		
		CartItem item=findCartItemById(id);
		User user=userService.findUserById(item.getUserId());
		
		
		if(user.getId().equals(userId)) {
			
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getQuantity()*item.getProduct().getDiscountedPrice());
			
			return cartItemRepository.save(item);
				
			
		}
		else {
			throw new CartItemException("You can't update  another users cart_item");
		}
		
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
		
		CartItem cartItem=cartItemRepository.isCartItemExist(cart, product, size, userId);


		return cartItem;
	}
	
	

	@Override
	public void removeCartItem(Long userId,Long cartItemId) throws CartItemException, UserException {
		
		System.out.println("userId- "+userId+" cartItemId "+cartItemId);
		
		CartItem cartItem=findCartItemById(cartItemId);
		
		User user=userService.findUserById(cartItem.getUserId());
		User reqUser=userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItem.getId());
			UserInteraction userInteraction = new UserInteraction();
			userInteraction.setUserId(cartItem.getUserId());
			userInteraction.setSellerId(cartItem.getProduct().getSellerShopId());
			userInteraction.setTimestamp(LocalDateTime.now());
			userInteraction.setAction(UserActions.ADD_TO_CART);
			userInteraction.setProductId(cartItem.getProduct().getId());
			userInteractionRepository.save(userInteraction);

		}
		else {
			throw new UserException("you can't remove anothor users item");
		}
		
	}

	@Override
	public CartItem findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItem> opt=cartItemRepository.findById(cartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartItemException("cartItem not found with id : "+cartItemId);
	}

}
