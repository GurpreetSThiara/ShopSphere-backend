package com.ecom.repository;

import com.ecom.modal.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionRepository  extends JpaRepository<Transaction, Long> {

    @Query("Select t from Transaction t where t.sellerId=:sellerId")
    public Page<Transaction> getTransactionsBySellerId(@Param("sellerId") Long sellerId ,	Pageable pageable );
}
