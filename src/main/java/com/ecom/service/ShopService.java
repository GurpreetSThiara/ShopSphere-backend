package com.ecom.service;

import com.ecom.modal.Seller;

import java.util.List;

public interface ShopService {

    public List<Seller> findNearByShops(double latitude , double longitude, double radius);
}
