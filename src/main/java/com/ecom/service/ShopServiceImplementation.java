package com.ecom.service;

import com.ecom.modal.Seller;
import com.ecom.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImplementation implements ShopService{
    @Autowired
    private SellerRepository sellerRepository;

    @Override
    public List<Seller> findNearByShops(double latitude, double longitude , double radius) {

      // in kilometers
        return sellerRepository.findNearByShops(latitude, longitude, radius);
    }
}
