package com.ecom.service;

import com.ecom.modal.Product;
import com.ecom.modal.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SellerService {




    public List<Product> getAllShopProducts(Long SellerShopId , int pageNumber ,int pageSize);

    public List<Shop> getAllShops( int pageNumber ,int pageSize);






}
