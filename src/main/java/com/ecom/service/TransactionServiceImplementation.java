package com.ecom.service;

import com.ecom.modal.Transaction;
import com.ecom.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionBySellerId(Long sellerId ,int pageNumber, int pageSize) {
       		Pageable pageable = PageRequest.of(pageNumber, pageSize);


        transactionRepository.getTransactionsBySellerId(sellerId ,pageable);
        return null;
    }
}
