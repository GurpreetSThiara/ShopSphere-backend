package com.ecom.service;

import com.ecom.modal.Seller;
import com.ecom.modal.User;
import com.ecom.repository.SellerRepository;
import com.ecom.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class CustomSellerDetails {

    private SellerRepository sellerRepository;

    public CustomSellerDetails(SellerRepository sellerRepository) {
        this.sellerRepository=sellerRepository;

    }


    public UserDetails loadSellerByUsername(String username) throws UsernameNotFoundException {

        Seller seller = sellerRepository.findByEmail(username);

        if(seller == null) {
            throw new UsernameNotFoundException("user not found with email "+username);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(seller.getEmail(),seller.getPassword(),authorities);
    }
}
