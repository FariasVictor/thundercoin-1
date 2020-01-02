package com.invillia.thundercoin.service;

import com.invillia.thundercoin.domain.Transaction;
import com.invillia.thundercoin.domain.request.TransactionRequest;
import com.invillia.thundercoin.domain.response.TransactionResponse;

import java.util.List;


public interface TransactionService {
    List<TransactionResponse> findAll();

    TransactionResponse findById(final Long id);

    Transaction save(TransactionRequest transactionRequest);
}
