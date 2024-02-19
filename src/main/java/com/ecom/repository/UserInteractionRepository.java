package com.ecom.repository;


import com.ecom.modal.UserInteraction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {
    @Query("Select i from UserInteraction i where i.sellerId=:sellerId")
    public Page<UserInteraction> getInteractionsBySellerId(@Param("sellerId") Long sellerId , Pageable pageable );
}
