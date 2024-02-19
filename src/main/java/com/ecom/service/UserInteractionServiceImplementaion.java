package com.ecom.service;

import com.ecom.modal.UserInteraction;
import com.ecom.repository.UserInteractionRepository;
import com.ecom.user.domain.UserActions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service

public class UserInteractionServiceImplementaion implements UserInteractionService {

    @Autowired
    private UserInteractionRepository userInteractionRepository;

    @Override
    public void saveShopVisitInteraction(Long userId, Long shopId) {
        UserInteraction userInteraction = new UserInteraction();
        userInteraction.setUserId(userId);
        userInteraction.setTimestamp(LocalDateTime.now());
        userInteraction.setSellerId(shopId);
        userInteraction.setAction(UserActions.SHOPVISIT);

        userInteractionRepository.save(userInteraction); // Save the interaction
    }

    @Override
    public Page<UserInteraction> getShopInteractionsById(Long shopId , int page , int size) {
        Pageable pageable = PageRequest.of(page , size);
     return   userInteractionRepository.getInteractionsBySellerId(shopId,pageable);

    }


}
