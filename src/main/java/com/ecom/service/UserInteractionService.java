package com.ecom.service;

import com.ecom.modal.UserInteraction;
import com.ecom.response.InteractionsRes;
import org.springframework.data.domain.Page;

public interface UserInteractionService {
    public void saveShopVisitInteraction(Long userId,Long shopId);

    public Page<UserInteraction> getShopInteractionsById(Long shopId, int page , int size);
}
