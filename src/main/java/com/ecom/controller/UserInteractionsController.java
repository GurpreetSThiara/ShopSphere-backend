package com.ecom.controller;

import com.ecom.exception.UserException;
import com.ecom.modal.User;
import com.ecom.modal.UserInteraction;
import com.ecom.response.InteractionsRes;
import com.ecom.service.UserInteractionService;
import com.ecom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/interactions")
class UserInteractionsController {

    @Autowired
    private UserInteractionService userInteractionService;

    @Autowired

    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<Page<UserInteraction>> getShopInteractions(@RequestHeader("Authorization") String jwt , @RequestParam Long shopId, @RequestParam int pageNumber , @RequestParam int pageSize){
        Page<UserInteraction> res =  userInteractionService.getShopInteractionsById(shopId, pageNumber,pageSize);
     return new ResponseEntity<Page<UserInteraction>>(res,HttpStatus.OK);
    }

    @PostMapping("/visit")
    public void saveInteractions(@RequestHeader("Authorization") String jwt, @RequestParam Long shopId) throws UserException {
        User user=userService.findUserProfileByJwt(jwt);
        if(user != null ){
            userInteractionService.saveShopVisitInteraction(user.getId(),shopId);

        }
    }
}