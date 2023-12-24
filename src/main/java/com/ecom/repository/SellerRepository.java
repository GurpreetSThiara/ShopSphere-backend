package com.ecom.repository;
import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    public Seller findByEmail(String email);

    @Query("SELECT s FROM Seller s")
    List<Seller> findAllSellers();

}
