package ecom.service;

import java.util.List;

import ecom.exception.ProductException;
import ecom.modal.Rating;
import ecom.modal.User;
import ecom.request.RatingRequest;

public interface RatingServices {
	
	public Rating createRating(RatingRequest req,User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);

}
