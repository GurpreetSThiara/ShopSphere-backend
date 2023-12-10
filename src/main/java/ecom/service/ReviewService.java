package ecom.service;

import java.util.List;

import ecom.exception.ProductException;
import ecom.modal.Review;
import ecom.modal.User;
import ecom.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
