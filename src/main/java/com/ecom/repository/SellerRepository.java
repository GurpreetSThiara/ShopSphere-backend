package com.ecom.repository;
import com.ecom.modal.Product;
import com.ecom.modal.Seller;
import com.ecom.modal.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SellerRepository extends JpaRepository<Seller, Long> {

    public Seller findByEmail(String email);

}
