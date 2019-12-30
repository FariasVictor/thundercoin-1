package com.invillia.ThunderCoin.service;

import com.invillia.ThunderCoin.domain.Transaction;
import com.invillia.ThunderCoin.domain.request.TransactionRequest;
import com.invillia.ThunderCoin.domain.response.TransactionResponse;
import org.springframework.stereotype.Service;

import java.util.List;


public interface TransactionService {
    List<TransactionResponse> findAll();

    TransactionResponse findById(final Long id);

    Transaction save(TransactionRequest transactionRequest);
}
