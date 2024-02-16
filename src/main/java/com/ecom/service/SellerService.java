package com.ecom.service;

import com.ecom.exception.UserException;
import com.ecom.modal.*;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SellerService {




    public List<Product> getAllShopProducts(Long SellerShopId , int pageNumber ,int pageSize);

    public List<Shop> getAllShops( int pageNumber ,int pageSize);
    public Seller findSellerProfileByJwt(String jwt) throws UserException;

    public Page<OrderItem> getAllShopOrders(Long SellerShopId, int pageNumber , int pageSize);






}
