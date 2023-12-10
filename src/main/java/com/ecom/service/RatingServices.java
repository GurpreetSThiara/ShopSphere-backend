package com.ecom.service;

import java.util.List;

import com.ecom.exception.ProductException;
import com.ecom.modal.Rating;
import com.ecom.modal.User;
import com.ecom.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
