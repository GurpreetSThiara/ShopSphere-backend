package com.ecom.service;

import java.util.List;

import com.ecom.exception.ProductException;
import com.ecom.modal.Review;
import com.ecom.modal.User;
import com.ecom.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
	
}
