package com.ecom.repository;
import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.modal.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    public Seller findByEmail(String email);

    @Query("SELECT s FROM Seller s")
    Page<Seller> findAllSellers(Pageable pageable);

    @Query("SELECT s FROM Seller s WHERE FUNCTION('ACOS', FUNCTION('COS', :latitude) * FUNCTION('COS', s.latitude) * FUNCTION('COS', s.longitude - :longitude) + FUNCTION('SIN', :latitude) * FUNCTION('SIN', s.latitude)) * 6371 <= :radius")
    List<Seller> findNearByShops(@Param("latitude") double latitude, @Param("longitude") double longitude, @Param("radius") double radius);



}
