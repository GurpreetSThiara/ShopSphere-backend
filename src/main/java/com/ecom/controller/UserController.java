package com.ecom.controller;

import com.ecom.modal.Seller;
import com.ecom.modal.User;
import com.ecom.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecom.exception.UserException;
import com.ecom.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/users")
public class UserController {
	
	private UserService userService;

	@Autowired
	private ShopService shopService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{

		System.out.println("/api/users/profile");
		User user=userService.findUserProfileByJwt(jwt);
		return new ResponseEntity<User>(user,HttpStatus.ACCEPTED);
	}

	@GetMapping("/nearByShops")
	public ResponseEntity<List<Seller>> getNearByShops(@RequestParam double latitude , @RequestParam double longitude ,  @RequestParam double radius){
		List<Seller> res = shopService.findNearByShops(latitude ,longitude , radius);
      return new ResponseEntity<>(res,HttpStatus.OK);
	}

}
