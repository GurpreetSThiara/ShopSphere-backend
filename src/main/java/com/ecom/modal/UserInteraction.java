package com.ecom.modal;


import com.ecom.user.domain.UserActions;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "user_interaction")
public class UserInteraction {
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "user_id")
    private Long userId;

    private UserActions action;

    private LocalDateTime timestamp;

    private Long productId;

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public  UserInteraction(){

    }

    public Long getInteractionId() {
        return interactionId;
    }

    public void setInteractionId(Long interactionId) {
        this.interactionId = interactionId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public UserActions getAction() {
        return action;
    }

    public void setAction(UserActions action) {
        this.action = action;
    }


}
