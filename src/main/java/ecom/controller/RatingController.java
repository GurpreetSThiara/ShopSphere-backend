package ecom.controller;

import java.util.List;

import ecom.modal.Rating;
import ecom.modal.User;
import ecom.request.RatingRequest;
import ecom.service.RatingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ecom.exception.ProductException;
import ecom.exception.UserException;
import ecom.service.UserService;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
	@Autowired
	private UserService userService;

	@Autowired
	private RatingServices ratingServices;
	
//	public RatingController(UserService userService,RatingServices ratingServices) {
//		this.ratingServices=ratingServices;
//		this.userService=userService;
//		// TODO Auto-generated constructor stub
//	}

	@PostMapping("/create")
	public ResponseEntity<Rating> createRatingHandler(@RequestBody RatingRequest req, @RequestHeader("Authorization") String jwt) throws UserException, ProductException{
		User user=userService.findUserProfileByJwt(jwt);
		Rating rating=ratingServices.createRating(req, user);
		return new ResponseEntity<>(rating,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/product/{productId}")
	public ResponseEntity<List<Rating>> getProductsReviewHandler(@PathVariable Long productId){
	
		List<Rating> ratings=ratingServices.getProductsRating(productId);
		return new ResponseEntity<>(ratings,HttpStatus.OK);
	}
}
