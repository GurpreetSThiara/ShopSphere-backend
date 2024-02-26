package com.ecom.service;

import com.ecom.exception.UserException;
import com.ecom.modal.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SellerService {




    public List<Product> getAllShopProducts(Long SellerShopId , int pageNumber ,int pageSize);

    public List<Shop> getAllShops( int pageNumber ,int pageSize);
    public Seller findSellerProfileByJwt(String jwt) throws UserException;

    public Page<OrderItem> getAllShopOrders(Long SellerShopId, int pageNumber , int pageSize);

    Page<OrderItem> getAllShopOrdersForAllTime(Long SellerShopId, int pageNumber, int pageSize);

    // Retrieve orders for the last month
    Page<OrderItem> getAllShopOrdersForLastMonth(Long SellerShopId, int pageNumber, int pageSize);

    // Retrieve orders for the last week
    Page<OrderItem> getAllShopOrdersForLastWeek(Long SellerShopId, int pageNumber, int pageSize);

    // Retrieve orders for the last 24 hours
    Page<OrderItem> getAllShopOrdersForLast24Hours(Long SellerShopId, int pageNumber, int pageSize);

    // Retrieve orders for the last year
    Page<OrderItem> getAllShopOrdersForLastYear(Long SellerShopId, int pageNumber, int pageSize);


    public Seller updateSeller(Long sellerShopId, Seller updatedSeller);

    public Map<String, List<OrderItem>> getShopOrdersByMonth(Long shopId, LocalDateTime lastYear);




}
