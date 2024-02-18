package com.ecom.modal;


import com.ecom.user.domain.UserActions;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "user_interaction")
public class UserInteraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;

    @Column(name = "seller_id")
    private Long sellerId;

    @Column(name = "user_id")
    private Long userId;

    private UserActions action;

    private Date timestamp;

    UserInteraction(){

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

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
