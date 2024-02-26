package com.ecom.modal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long sellerShopId;

    // Personal Information
    private String firstName;
    private String LastName;
    private String email;
    private String mobile;
    private String password;

    // Business Information
    private String shopName;
    private String description;
    private String websiteUrl;
    private String logoImageUrl;

    // Location Information
    private String streetAddress;
    private String city;
    private String state;
    private String zipCode;
    private String country;
    private double latitude;
    private double longitude;

    // Contact Information
    private String phoneNumber;
    private String alternatePhoneNumber;
    private String faxNumber;

    // Business Registration and Tax Information
    private String businessRegistrationNumber;
    private String taxId;

    // Social Media Profiles
    private String facebookProfile;
    private String twitterProfile;
    private String instagramProfile;

    // Payment Information
    private String bankAccountDetails;
    private String paypalEmail;

    // Operating Hours
    private String openingTime;
    private String closingTime;
    private String daysOfOperation;

    private Boolean isShopOpen;

    public Boolean getShopOpen() {
        return isShopOpen;
    }

    public void setShopOpen(Boolean shopOpen) {
        isShopOpen = shopOpen;
    }

    public Seller(Long sellerShopId, String firstName, String lastName, String email, String mobile, String password, String shopName, String description, String websiteUrl, String logoImageUrl, String streetAddress, String city, String state, String zipCode, String country, double latitude, double longitude, String phoneNumber, String alternatePhoneNumber, String faxNumber, String businessRegistrationNumber, String taxId, String facebookProfile, String twitterProfile, String instagramProfile, String bankAccountDetails, String paypalEmail, String openingTime, String closingTime, String daysOfOperation) {
        this.sellerShopId = sellerShopId;
        this.firstName = firstName;
        LastName = lastName;
        this.email = email;
        this.mobile = mobile;
        this.password = password;
        this.shopName = shopName;
        this.description = description;
        this.websiteUrl = websiteUrl;
        this.logoImageUrl = logoImageUrl;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phoneNumber = phoneNumber;
        this.alternatePhoneNumber = alternatePhoneNumber;
        this.faxNumber = faxNumber;
        this.businessRegistrationNumber = businessRegistrationNumber;
        this.taxId = taxId;
        this.facebookProfile = facebookProfile;
        this.twitterProfile = twitterProfile;
        this.instagramProfile = instagramProfile;
        this.bankAccountDetails = bankAccountDetails;
        this.paypalEmail = paypalEmail;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.daysOfOperation = daysOfOperation;
    }

    public Seller() {
    }

    public Long getSellerShopId() {
        return sellerShopId;
    }

    public void setSellerShopId(Long sellerShopId) {
        this.sellerShopId = sellerShopId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public String getLogoImageUrl() {
        return logoImageUrl;
    }

    public void setLogoImageUrl(String logoImageUrl) {
        this.logoImageUrl = logoImageUrl;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAlternatePhoneNumber() {
        return alternatePhoneNumber;
    }

    public void setAlternatePhoneNumber(String alternatePhoneNumber) {
        this.alternatePhoneNumber = alternatePhoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getBusinessRegistrationNumber() {
        return businessRegistrationNumber;
    }

    public void setBusinessRegistrationNumber(String businessRegistrationNumber) {
        this.businessRegistrationNumber = businessRegistrationNumber;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }

    public String getFacebookProfile() {
        return facebookProfile;
    }

    public void setFacebookProfile(String facebookProfile) {
        this.facebookProfile = facebookProfile;
    }

    public String getTwitterProfile() {
        return twitterProfile;
    }

    public void setTwitterProfile(String twitterProfile) {
        this.twitterProfile = twitterProfile;
    }

    public String getInstagramProfile() {
        return instagramProfile;
    }

    public void setInstagramProfile(String instagramProfile) {
        this.instagramProfile = instagramProfile;
    }

    public String getBankAccountDetails() {
        return bankAccountDetails;
    }

    public void setBankAccountDetails(String bankAccountDetails) {
        this.bankAccountDetails = bankAccountDetails;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public String getDaysOfOperation() {
        return daysOfOperation;
    }

    public void setDaysOfOperation(String daysOfOperation) {
        this.daysOfOperation = daysOfOperation;
    }
}
