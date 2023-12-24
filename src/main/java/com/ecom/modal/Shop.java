package com.ecom.modal;

import java.util.List;

public class Shop {
    private String ShopName;
    private String Owner;
    private String description;
    private List<Product> products;
    private String email;
    private String phone;
    private String website;
    private String facebook;
    private String instagram;
    private String x;

    public Shop() {
    }

    public Shop(String shopName, String owner, String description, List<Product> products, String email, String phone, String website, String facebook, String instagram, String x) {
        ShopName = shopName;
        Owner = owner;
        this.description = description;
        this.products = products;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.facebook = facebook;
        this.instagram = instagram;
        this.x = x;
    }

    public String getShopName() {
        return ShopName;
    }

    public void setShopName(String shopName) {
        ShopName = shopName;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String owner) {
        Owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    public String getInstagram() {
        return instagram;
    }

    public void setInstagram(String instagram) {
        this.instagram = instagram;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }
}
