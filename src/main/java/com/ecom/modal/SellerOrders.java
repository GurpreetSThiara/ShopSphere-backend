package com.ecom.modal;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;

public class SellerOrders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Long orderId;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private Long SellerId;


    public SellerOrders(){}

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getSellerId() {
        return SellerId;
    }

    public void setSellerId(Long sellerId) {
        SellerId = sellerId;
    }
}
