package com.ecom.modal;


import com.ecom.user.domain.OrderStatus;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    private Long sellerId;

    private Long productId;

    private long buyerId;

    private double price;

    private Date timeStamp;

    private OrderStatus status;

    public Transaction() {
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(long buyerId) {
        this.buyerId = buyerId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    private Date deliveryDate;

    public Transaction(Long transactionId, Long sellerId, Long productId, long buyerId, double price, Date timeStamp, OrderStatus status, Date deliveryDate) {
        this.transactionId = transactionId;
        this.sellerId = sellerId;
        this.productId = productId;
        this.buyerId = buyerId;
        this.price = price;
        this.timeStamp = timeStamp;
        this.status = status;
        this.deliveryDate = deliveryDate;
    }
}
