package com.ecom.repository;


import com.ecom.modal.Order;
import com.ecom.modal.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId")
    public Page<OrderItem> getShopOrders(@Param("shopId") Long shopId ,Pageable pageable);


}
