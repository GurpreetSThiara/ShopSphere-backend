package com.ecom.request;

public class PaymentRequest {

    private String cardNumber;
    private Integer expMonth;
    private Integer expYear;
    private String cvc;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Integer getExpMonth() {
        return expMonth;
    }

    public void setExpMonth(Integer expMonth) {
        this.expMonth = expMonth;
    }

    public Integer getExpYear() {
        return expYear;
    }

    public void setExpYear(Integer expYear) {
        this.expYear = expYear;
    }

    public String getCvc() {
        return cvc;
    }

    public void setCvc(String cvc) {
        this.cvc = cvc;
    }

    public PaymentRequest(String cardNumber, Integer expMonth, Integer expYear, String cvc) {
        this.cardNumber = cardNumber;
        this.expMonth = expMonth;
        this.expYear = expYear;
        this.cvc = cvc;
    }

    public PaymentRequest() {
    }
    // getters and setters
}
