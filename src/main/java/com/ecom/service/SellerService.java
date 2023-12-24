package com.ecom.service;

import com.ecom.modal.Product;
import com.ecom.modal.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SellerService {




    public List<Product> getAllShopProducts(Long SellerShopId);

    public List<Shop> getAllShops();






}
