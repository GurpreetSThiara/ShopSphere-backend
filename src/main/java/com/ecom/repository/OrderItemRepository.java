package com.ecom.repository;


import com.ecom.modal.Order;
import com.ecom.modal.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId ORDER BY o.createdAt DESC")
    public Page<OrderItem> getShopOrders(@Param("shopId") Long shopId ,Pageable pageable);

    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId")
    Page<OrderItem> getShopOrdersForAllTime(@Param("shopId") Long shopId, Pageable pageable);

    // Retrieve orders for the last month
    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId AND o.createdAt >= :lastMonth")
    Page<OrderItem> getShopOrdersForLastMonth(@Param("shopId") Long shopId, @Param("lastMonth") LocalDateTime lastMonth, Pageable pageable);

    // Retrieve orders for the last week
    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId AND o.createdAt >= :lastWeek")
    Page<OrderItem> getShopOrdersForLastWeek(@Param("shopId") Long shopId, @Param("lastWeek") LocalDateTime lastWeek, Pageable pageable);

    // Retrieve orders for the last 24 hours
    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId AND o.createdAt >= :last24Hours")
    Page<OrderItem> getShopOrdersForLast24Hours(@Param("shopId") Long shopId, @Param("last24Hours") LocalDateTime last24Hours, Pageable pageable);

    // Retrieve orders for the last year
    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId AND o.createdAt >= :lastYear")
    Page<OrderItem> getShopOrdersForLastYear(@Param("shopId") Long shopId, @Param("lastYear") LocalDateTime lastYear, Pageable pageable);

    @Query("SELECT o FROM OrderItem o WHERE o.sellerId = :shopId AND o.createdAt >= :lastYear")
    List<OrderItem> getShopOrdersForLastYearForGraph(@Param("shopId") Long shopId, @Param("lastYear") LocalDateTime lastYear);


}
