package com.ecom.service;

import com.ecom.modal.Transaction;

public interface TransactionService {
    public void addTransaction(Transaction transaction);

    public Transaction getTransactionBySellerId(Long sellerId, int pageNumber, int pageSize);
}
