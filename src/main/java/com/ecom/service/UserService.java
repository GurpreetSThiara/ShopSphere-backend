package com.ecom.service;

import com.ecom.exception.UserException;
import com.ecom.modal.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt) throws UserException;



}
